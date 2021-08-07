package com.example.productosapp.data

interface ProductRepository{
    suspend fun getProducts():List<Product>
    suspend fun addProduct(product: Product)
    suspend fun editProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun getDetailProduct(id: Int): Product?
    suspend fun login(email:String, password:String): User?
    suspend fun register(user:User)
}


