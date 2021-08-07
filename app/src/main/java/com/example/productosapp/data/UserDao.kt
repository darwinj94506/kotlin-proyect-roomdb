package com.example.productosapp.data
import androidx.room.*

@Dao
interface UserDao {

    @Query("Select * From User WHERE username = :username AND password = :password limit 1")
    suspend fun login(username: String, password:String): User?

    @Insert
    suspend fun register(user: User): Long
}