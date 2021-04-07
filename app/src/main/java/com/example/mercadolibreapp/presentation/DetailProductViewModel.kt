package com.example.mercadolibreapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mercadolibreapp.domain.Product
import io.reactivex.disposables.CompositeDisposable

class DetailProductViewModel(private val product: Product?=null):ViewModel() {

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<DetailListNavigation>>()
    val events: LiveData<Event<DetailListNavigation>> get() = _events

    private val _productValues = MutableLiveData<Product>()
    val productValues: LiveData<Product> get() = _productValues

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun onProductValidation() {
        if (product == null) {
            _events.value = Event(DetailListNavigation.CloseActivity)
            return
        }

        _productValues.value = product
    }


    sealed class DetailListNavigation(){
        data class ShowProductError(val error: Throwable): DetailListNavigation()
        data class ShowProductInf(val placeList: List<Product>): DetailListNavigation()
        object HideProgressBar : DetailListNavigation()
        object CloseActivity : DetailListNavigation()
        object ShowProgressBar : DetailListNavigation()
    }


}