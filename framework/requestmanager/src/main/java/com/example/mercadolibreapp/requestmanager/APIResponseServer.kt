package com.example.mercadolibreapp.requestmanager

import android.os.Parcelable
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_ADDRESS
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_AVAILABLE_QUANTITY
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_CITY_ID
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_CITY_NAME
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_CONDITION
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_FREE_SHIPPING8
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_ID
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_PRICE
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_RESULTS
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_SELLER
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_SHIPPING
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_STATE_ID
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_STATE_NAME
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_THUMBNAIL
import com.example.mercadolibreapp.requestmanager.APIConstants.KEY_TITLE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//Example of serializable
@Parcelize
data class ProductServer(
    @SerializedName(KEY_ID) val id: String?,
    @SerializedName(KEY_TITLE) val title: String,
    @SerializedName(KEY_SELLER) val seller: SellerServer,
    @SerializedName(KEY_PRICE) val price: Int,
    @SerializedName(KEY_AVAILABLE_QUANTITY) val available_quantity: Int,
    @SerializedName(KEY_THUMBNAIL) val thumbnail: String,
    @SerializedName(KEY_CONDITION) val condition: String,
    @SerializedName(KEY_ADDRESS) val address: AddressServer,
    @SerializedName(KEY_SHIPPING) val shipping: ShippingServer


): Parcelable

@Parcelize
data class ProductResponseServer(
    @SerializedName(KEY_RESULTS)
    val results: List<ProductServer>
): Parcelable

@Parcelize
data class AddressServer(
    @SerializedName(KEY_STATE_ID) val stateId: String,
    @SerializedName(KEY_STATE_NAME) val stateName: String,
    @SerializedName(KEY_CITY_ID) val cityId: String,
    @SerializedName(KEY_CITY_NAME) val cityName: String,
): Parcelable

@Parcelize
data class SellerServer(
    @SerializedName(KEY_ID) val id: String,
): Parcelable

@Parcelize
data class ShippingServer(
    @SerializedName(KEY_FREE_SHIPPING8) val freeShipping: Boolean,
): Parcelable
