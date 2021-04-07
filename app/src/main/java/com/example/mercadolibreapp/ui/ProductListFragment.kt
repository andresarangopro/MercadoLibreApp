package com.example.mercadolibreapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mercadolibreapp.R
import com.example.mercadolibreapp.adapters.ProductGridAdapter
import com.example.mercadolibreapp.databinding.FragmentProductListBinding
import com.example.mercadolibreapp.di.ProductListBySearchComponent
import com.example.mercadolibreapp.di.ProductListBySearchModule
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.parcelable.toProductParcelable
import com.example.mercadolibreapp.presentation.ProductListViewModel
import com.example.mercadolibreapp.utils.*
import kotlinx.android.synthetic.main.activity_list_products.*
import kotlinx.android.synthetic.main.fragment_product_list.*


class ProductListFragment : Fragment(),Interactions.OnProductListFragmentListener {

    //private lateinit var binding: ActivityListProductsBinding
    private lateinit var productListBySearchComponent: ProductListBySearchComponent
    private lateinit var listener: Interactions.OnProductListFragmentListener
    private lateinit var productGridAdapter: ProductGridAdapter

    private val productListViewModel: ProductListViewModel by lazy{
        getViewModel {
            productListBySearchComponent.productListBySearchViewModel
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListBySearchComponent = context?.app!!.component.inject(ProductListBySearchModule())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as Interactions.OnProductListFragmentListener
        }catch (e: ClassCastException){
            throw ClassCastException("$context must implement OnCharacterListFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // productRe = PlaceRequest(BASE_API_URL)

        return DataBindingUtil.inflate<FragmentProductListBinding>(
            inflater,
            R.layout.fragment_product_list,
            container,
            false
        ).apply {
            lifecycleOwner = this@ProductListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Navigation.findNavController(this,R.id.navigation_graph).navigate()
        productGridAdapter = ProductGridAdapter { product ->
            openProductDetail(product)

        }
        productGridAdapter.setHasStableIds(true)

        rvPlaceList.run{
            // addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.list_item_padding))
            adapter = productGridAdapter
        }

        productListBySearchComponent = context?.app!!.component.inject(
            ProductListBySearchModule()
        )

        searchProduct.onQueryTextChanged {
            Log.d("ProductChange","${it}")
            productListViewModel.onGetProductsBySearch(it,10)
        }

        productListViewModel.events.observe(
            viewLifecycleOwner, Observer { events ->
                events?.getContentIfNotHandled()?.let{navigation->
                    when(navigation){
                        is ProductListViewModel.ProductListNavigation.ShowProductError->{
                            context?.showLongToast("Error ")
                        }
                        is ProductListViewModel.ProductListNavigation.ShowProductList-> navigation.run {
                            productGridAdapter.newSearchData(productList)
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

    override fun openProductDetail(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.EXTRA_PRODUCT,product.toProductParcelable())
        findNavController().navigate(R.id.detailProductFragment,bundle)
    }
}