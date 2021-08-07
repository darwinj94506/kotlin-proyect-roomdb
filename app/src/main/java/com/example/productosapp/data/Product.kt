package com.example.productosapp.data

import java.util.*
import java.util.Random
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    val name: String,
    val description: String,
    val inStock: Boolean = false,
    val brand: String
)

val TASK_DIFF = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}

