package com.example.productosapp.data
import android.content.Context

class RoomProductRepository(private val context: Context) : ProductRepository {

    private val productDao = AppDatabase.getAppDatabase(context)!!.productDao()
    private val userDao = AppDatabase.getAppDatabase(context)!!.userDao()


    override suspend fun getProducts(): List<Product> {
        return productDao.getProducts()
    }

    override suspend fun getDetailProduct(producId: Int): Product? {
        return productDao.getProductById(producId)
    }

    override suspend fun login(email:String, password:String): User? {
        return userDao.login(email, password)
    }

    override suspend fun register(user: User) {
        userDao.register(user)
    }

    override suspend fun addProduct(product: Product) {
        productDao.insert(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDao.delete(product)
    }

    override suspend fun editProduct(product: Product) {
        productDao.update(product)
    }
}