package com.example.mercadolibreapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mercadolibreapp.R
import androidx.navigation.NavController
import com.example.mercadolibreapp.domain.Product

class MainActivity : AppCompatActivity(),Interactions.OnProductListFragmentListener{

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_list_products)

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun openProductDetail(product: Product) {
        return
    }
}