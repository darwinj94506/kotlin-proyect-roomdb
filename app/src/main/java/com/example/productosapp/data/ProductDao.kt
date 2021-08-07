package com.example.productosapp.data
import androidx.room.*

@Dao
interface ProductDao {
    @Query("Select * FROM Product")
    suspend fun getProducts(): List<Product>

    @Query("Select * From Product WHERE id = :id limit 1")
    suspend fun getProductById(id: Int): Product?

    @Insert
    suspend fun insert(product: Product): Long

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

}