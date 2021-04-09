package com.example.mercadolibreapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mercadolibreapp.domain.Address
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.domain.Seller
import com.example.mercadolibreapp.domain.Shipping
import com.example.mercadolibreapp.presentation.Event
import com.example.mercadolibreapp.presentation.ProductListViewModel
import com.example.mercadolibreapp.presentation.ProductListViewModel.ProductListNavigation
import com.example.mercadolibreapp.usecases.GetProductsBySearchUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductListViewModelTest() {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var getProductsBySearchUseCase: GetProductsBySearchUseCase

    @Mock
    lateinit var eventObserver: Observer<Event<ProductListNavigation>>

    private lateinit var productListViewModel: ProductListViewModel

    @Before
    fun setUp() {
        productListViewModel = ProductListViewModel(
            getProductsBySearchUseCase
        )
    }

    @Test
    fun `onGetProductsBySearch should return an expected success list of products`() {
        //GIVEN
        val expectedResult = listOf(mockedProduct.copy(id = ""))
        given(getProductsBySearchUseCase.invoke(any(),any())).willReturn(Single.just(expectedResult))

        productListViewModel.events.observeForever(eventObserver)

        //WHEN
        productListViewModel.onGetProductsBySearch("telefono",10)

        //THEN
        verify(eventObserver).onChanged(Event(ProductListViewModel.ProductListNavigation.ShowProductList(expectedResult)))
    }
}

val mockedShipping= Shipping(
    false
)

val mockedAddress=  Address(
    "",
    "",
    "",
    ""
)

val mockedSeller = Seller(
    ""
)

val mockedProduct = Product(
    "",
    "",
    mockedSeller,
    0,
    0,
    "",
    "",
    mockedAddress,
    mockedShipping

)