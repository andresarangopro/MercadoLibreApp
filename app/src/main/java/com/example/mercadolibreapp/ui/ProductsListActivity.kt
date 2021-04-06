package com.example.mercadolibreapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mercadolibreapp.R
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mercadolibreapp.adapters.ProductGridAdapter
import com.example.mercadolibreapp.databinding.ActivityListProductsBinding
import com.example.mercadolibreapp.di.ProductListBySearchComponent
import com.example.mercadolibreapp.di.ProductListBySearchModule
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.presentation.ProductListViewModel
import com.example.mercadolibreapp.utils.*
import kotlinx.android.synthetic.main.activity_list_products.*

class ProductsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListProductsBinding
    private lateinit var productListBySearchComponent: ProductListBySearchComponent
    private lateinit var listener: OnProductListFragmentListener
    private lateinit var productGridAdapter: ProductGridAdapter

    private val productListViewModel: ProductListViewModel by lazy{
        getViewModel {
            productListBySearchComponent.productListBySearchViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_products)
        binding.lifecycleOwner = this@ProductsListActivity
        //Navigation.findNavController(this,R.id.navigation_graph).navigate()
        productGridAdapter = ProductGridAdapter { product ->
            listener.openProductDetail(product)
        }
        productGridAdapter.setHasStableIds(true)

        rvPlaceList.run{
           // addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))

            adapter = productGridAdapter
        }

        productListBySearchComponent = applicationContext!!.app.component.inject(
            ProductListBySearchModule()
        )
        productListViewModel.onGetProductsBySearch("Celular", 10)
        binding.searchProduct.onQueryTextChanged {
            Log.d("ProductChange","${it}")
            productListViewModel.onGetProductsBySearch(it,10)
        }

        productListViewModel.events.observe(
            this, Observer { events ->
                events?.getContentIfNotHandled()?.let{navigation->
                    when(navigation){
                        is ProductListViewModel.ProductListNavigation.ShowProductError->{
                            applicationContext?.showLongToast("Error ")
                        }
                        is ProductListViewModel.ProductListNavigation.ShowProductList-> navigation.run {
                            productGridAdapter.addData(productList)
                        }
                        ProductListViewModel.ProductListNavigation.HideLoading->{
                            srwProductList.isRefreshing = false
                        }
                        ProductListViewModel.ProductListNavigation.ShowLoading->{
                            srwProductList.isRefreshing = true
                        }
                    }

                }
            }
        )
    }

    //region Inner Classes & Interfaces
    interface OnProductListFragmentListener {
        fun openProductDetail(product: Product)
    }
}