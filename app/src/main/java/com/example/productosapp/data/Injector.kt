package com.example.productosapp.data

import android.content.Context
import com.example.productosapp.data.RoomProductRepository
import com.example.productosapp.data.ProductRepository

object Injector {

    fun provideRepository(context: Context): ProductRepository {
        return RoomProductRepository(context)
    }
}
