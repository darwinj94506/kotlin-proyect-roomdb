package com.example.productosapp
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productosapp.data.Injector
import com.example.productosapp.data.RoomProductRepository
import com.example.productosapp.data.Product
import kotlinx.coroutines.launch
import java.util.*

class ProductDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Injector.provideRepository(application)
    private var product: Product? = null

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val inStock = MutableLiveData<Boolean>()
    val brand = MutableLiveData<String>()

    val createdProduct = MutableLiveData<Boolean>()

    fun start(productId: Int) {

        viewModelScope.launch {
            if (productId > 0) {
                product = repository.getDetailProduct(productId) ?: return@launch
                name.value = product!!.name
                description.value = product!!.description
                inStock.value = product!!.inStock
                brand.value = product!!.brand
            }
        }
    }

    fun createProduct() {

        val name = name.value ?: return
        val description = description.value ?: return
        val brand = brand.value ?: return

        viewModelScope.launch {

            if (product != null) {
                val editProduct = product!!.copy(name = name, description = description, inStock=true, brand= brand)
                repository.editProduct(editProduct)
                createdProduct.value = true
            } else {
                repository.addProduct(Product(name = name, description = description, inStock=true, brand= brand))
                createdProduct.value = true
                val data = repository.getProducts()
            }

        }

    }

}
