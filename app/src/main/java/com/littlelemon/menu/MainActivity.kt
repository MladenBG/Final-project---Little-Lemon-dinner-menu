package com.littlelemon.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {

    // The productsList declaration was moved to the ProductsWarehouse singleton object

    private val productsState: MutableStateFlow<Products> =
        // Update the usage of productsList to use the singleton object
        MutableStateFlow(Products(ProductsWarehouse.productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitUI() }
    }

    @Composable
    fun InitUI() {
        val products by productsState.collectAsState()
        // Pass the startProductActivity function reference to ProductsGrid
        ProductsGrid(
            products = products,
            startProductActivity = this::startProductActivity
        )
    }

    private fun startProductActivity(productItem: ProductItem) {
        // Instantiate intent and pass extra parameter from product
        val intent = Intent(this, ProductActivity::class.java).apply {
            putExtra(ProductActivity.KEY_TITLE, productItem.title)
            putExtra(ProductActivity.KEY_PRICE, productItem.price)
            putExtra(ProductActivity.KEY_IMAGE, productItem.image)
            putExtra(ProductActivity.KEY_CATEGORY, productItem.category)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu ?: return true
        menuInflater.inflate(R.menu.products_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting) {
            val type = when (item.itemId) {
                R.id.sort_a_z -> SortType.Alphabetically
                R.id.sort_price_asc -> SortType.PriceAsc
                R.id.sort_price_desc -> SortType.PriceDesc
                else -> SortType.Alphabetically
            }
            productsState.update {
                Products(
                    SortHelper().sortProducts(
                        type,
                        // Update the usage of productsList here as well
                        ProductsWarehouse.productsList
                    )
                )
            }
        } else if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> FilterType.All
                R.id.filter_drinks -> FilterType.Drinks
                R.id.filter_food -> FilterType.Food
                R.id.filter_dessert -> FilterType.Dessert
                else -> FilterType.All
            }
            productsState.update {
                Products(
                    FilterHelper().filterProducts(
                        type,
                        // Update the usage of productsList here as well
                        ProductsWarehouse.productsList
                    )
                )
            }
        }
        return true
    }
}