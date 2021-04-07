package com.example.mercadolibreapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mercadolibreapp.R
import com.example.mercadolibreapp.databinding.FragmentDetailProductBinding
import com.example.mercadolibreapp.databinding.FragmentProductListBinding
import com.example.mercadolibreapp.di.DetailProductComponent
import com.example.mercadolibreapp.di.DetailProductModule
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.imagemanager.bindCircularImageUrl
import com.example.mercadolibreapp.parcelable.ProductParcelable
import com.example.mercadolibreapp.parcelable.toProductDomain
import com.example.mercadolibreapp.parcelable.toProductParcelable
import com.example.mercadolibreapp.presentation.DetailProductViewModel
import com.example.mercadolibreapp.utils.Constants
import com.example.mercadolibreapp.utils.app
import com.example.mercadolibreapp.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_detail_product.*


class DetailProductFragment : Fragment() {

    private lateinit var productDetailComponent: DetailProductComponent
    private lateinit var binding: FragmentDetailProductBinding
    private lateinit var product: Product

    private val detailProductViewModel: DetailProductViewModel by lazy{
        getViewModel {
            productDetailComponent.detailProductViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        product = requireArguments().getParcelable<ProductParcelable>(Constants.EXTRA_PRODUCT)!!.toProductDomain()
        productDetailComponent = requireContext()!!.app.component.inject(DetailProductModule(product))
        detailProductViewModel.productValues.observe(this, Observer(this::loadCharacter))
        detailProductViewModel.onProductValidation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentDetailProductBinding>(
            inflater,
            R.layout.fragment_detail_product,
            container,
            false
        )
        return binding.apply {
            lifecycleOwner = this@DetailProductFragment
        }.root
    }

    private fun loadCharacter(product: Product){
        binding.productImage.bindCircularImageUrl(
            url = product.thumbnail,
            placeholder = R.drawable.ic_camera_alt_black,
            errorPlaceholder = R.drawable.ic_broken_image_black
        )

        binding.productDataName = product.title
        binding.productPrice = product.price.toString()
        binding.productSellerName = "seller"
        binding.productStock = product.available_quantity.toString()
        binding.productStateName = product.address.stateName
        binding.productCityName = product.address.cityName
        binding.productShippingType = if (product.shipping.freeShipping )"Gratis" else "Costo"

    }

}