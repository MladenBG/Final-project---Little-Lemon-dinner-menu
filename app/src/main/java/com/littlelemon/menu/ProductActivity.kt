package com.littlelemon.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Declare title variable with the value from intent using getStringExtra
        val title = intent.getStringExtra(KEY_TITLE) ?: ""

        // Declare a price variable and get its value from intent using getDoubleExtra
        val price = intent.getDoubleExtra(KEY_PRICE, 0.0)

        // Declare the category and image variables
        val category = intent.getStringExtra(KEY_CATEGORY) ?: ""
        val image = intent.getIntExtra(KEY_IMAGE, 0)

        // Create a productItem variable and pass the values to its constructor
        val productItem = ProductItem(
            title = title,
            price = price,
            category = category,
            image = image
        )

        // Pass the productItem variable to the ProductDetails composable function
        setContent {
            ProductDetails(productItem = productItem)
        }
    }

    // Declare a companion object inside the ProductActivity with four constant values
    companion object {
        const val KEY_TITLE = "title"
        const val KEY_PRICE = "price"
        const val KEY_IMAGE = "image"
        const val KEY_CATEGORY = "category"
    }
}