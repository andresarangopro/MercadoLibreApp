package com.example.mercadolibreapp.parcelable

import android.os.Parcelable
import com.example.mercadolibreapp.domain.Address
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.domain.Seller
import com.example.mercadolibreapp.domain.Shipping
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductParcelable(
    val id: String?,
    val title: String,
    val seller: SellerParcelable,
    val price: Int,
    val available_quantity: Int,
    val thumbnail: String,
    val condition: String,
    val address: AddressParcelable,
    val shipping: ShippingParcelable
): Parcelable


@Parcelize
data class AddressParcelable(
   val stateId: String,
   val stateName: String,
   val cityId: String,
   val cityName: String,
): Parcelable

@Parcelize
data class SellerParcelable(
    val id: String,
): Parcelable

@Parcelize
data class ShippingParcelable(
   val freeShipping: Boolean,
): Parcelable

////////////////////////
//Domain to parcelable
////////////////////////

fun Product.toProductParcelable() = ProductParcelable(
    id ,
    title,
    seller.toSellerParcelable(),
    price,
    available_quantity,
    thumbnail,
    condition,
    address.toAddressParcelable(),
    shipping.toShippingParcelable()
)

fun Seller.toSellerParcelable() = SellerParcelable(
    id
)

fun Address.toAddressParcelable() = AddressParcelable(
    stateId,
    stateName,
    cityId,
    cityName
)

fun Shipping.toShippingParcelable() = ShippingParcelable(
    freeShipping
)
////////////////////////
//Parcelable to domain
////////////////////////

fun ProductParcelable.toProductDomain() = Product(
    id ,
    title,
    seller.toSellerDomain(),
    price,
    available_quantity,
    thumbnail,
    condition,
    address.toAddressDomain(),
    shipping.toShippingDomain()
)


fun SellerParcelable.toSellerDomain() = Seller(
    id
)

fun AddressParcelable.toAddressDomain() = Address(
    stateId,
    stateName,
    cityId,
    cityName
)

fun ShippingParcelable.toShippingDomain() = Shipping(
    freeShipping
)