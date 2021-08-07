package com.example.productosapp.data
import java.util.Random
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val username: String,
    val password: String
)

