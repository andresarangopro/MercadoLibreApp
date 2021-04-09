package com.example.mercadolibreapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mercadolibreapp.R
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.parcelable.toProductParcelable
import com.example.mercadolibreapp.utils.Constants


class MainActivity : AppCompatActivity(),Interactions.OnProductListFragmentListener{

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_list_products)
                  // setupActionBarWithNavController(findNavController(R.id.navigation_graph))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun openProductDetail(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.EXTRA_PRODUCT,product.toProductParcelable())
        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.detailProductFragment,bundle)
        overridePendingTransition(R.anim.entry, R.anim.exit)
    }
}