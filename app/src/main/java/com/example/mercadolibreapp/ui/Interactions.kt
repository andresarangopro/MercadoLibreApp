package com.example.mercadolibreapp.ui

import com.example.mercadolibreapp.domain.Product

interface Interactions {

    //region Inner Classes & Interfaces
    interface OnProductListFragmentListener {
        fun openProductDetail(product: Product)
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }
}