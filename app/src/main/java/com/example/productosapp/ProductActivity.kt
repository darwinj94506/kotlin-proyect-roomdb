package com.example.productosapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, ProductFragment.newInstance(), "ProductFragment")
            .commit()
    }
}