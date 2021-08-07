package com.example.productosapp
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productosapp.data.Injector
import com.example.productosapp.data.Product
import kotlinx.coroutines.launch

class ProductViewModel(application: Application):  AndroidViewModel(application){
    private val repository = Injector.provideRepository(application)
    private val mProduct = MutableLiveData<List<Product>>()
    val product: LiveData<List<Product>>
        get() = mProduct

    private val mEventOpenDetail = SingleLiveEvent<Product>()
    val eventOpenDetail: LiveData<Product>
        get() = mEventOpenDetail

    init {
        viewModelScope.launch {
            mProduct.value = repository.getProducts()
        }
    }

    fun openDetailProduct(product: Product) {
        mEventOpenDetail.value = product
    }

    fun deleteProduct(p: Product) {
        viewModelScope.launch {
            repository.deleteProduct(p)
            mProduct.value = repository.getProducts()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val products = repository.getProducts()
            mProduct.value = products
        }
    }

}