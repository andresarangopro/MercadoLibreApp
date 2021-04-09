package com.example.mercadolibreapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.usecases.GetProductsBySearchUseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers

class ProductListViewModel(
    private val getProductsBySearchUsesCase: GetProductsBySearchUseCase
): ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<ProductListNavigation>>()
    val events: LiveData<Event<ProductListNavigation>> get() = _events

    private val _productInput = MutableLiveData<String>()
    val productInput:LiveData<String> get() = _productInput

    private var currentSize = 10
    private var isLastPage = false
    private var isLoading = false

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object{
        private const val SIZE = 10
    }

    fun setSearch(search: String) {
        _productInput.value = search
    }

    fun setterCurrentSize() {
        currentSize = 10
    }


    sealed class ProductListNavigation(){
        data class ShowProductError(val error: Throwable): ProductListNavigation()
        data class ShowProductList(val productList:List<Product>): ProductListNavigation()
        object HideLoading : ProductListNavigation()
        object ShowLoading : ProductListNavigation()
    }

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }
        currentSize += 10
        onGetProductsBySearch(productInput.value!!,currentSize)
    }

    fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= SIZE
    }

    fun toPrice(int:Int):String{
        return "Precio: $${int} "
    }

    fun onGetProductsBySearch(productInput:String, limit:Int){
        disposable.add(
            getProductsBySearchUsesCase
                .invoke(productInput,limit)
                .doOnSubscribe {
                    _events.value  = Event(ProductListNavigation.ShowLoading)
                }
                .subscribe({ productList ->
                    _events.value  = Event(ProductListNavigation.HideLoading)
                    _events.value  = Event(ProductListNavigation.ShowProductList(productList))

                }, { error ->
                    //isLastPage = true
                    _events.value  = Event(ProductListNavigation.HideLoading)
                    _events.value  = Event(ProductListNavigation.ShowProductError(error))
                })
        )
    }

}

