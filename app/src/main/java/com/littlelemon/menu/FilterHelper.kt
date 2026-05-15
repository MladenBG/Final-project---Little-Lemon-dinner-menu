package com.littlelemon.menu

class FilterHelper {

    // Implement actual logic of filtering productsList based on category using the sealed class
    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Drinks -> productsList.filter { product ->
                product.category == "Drinks"
            }
            FilterType.Food -> productsList.filter { product ->
                product.category == "Food"
            }
            FilterType.Dessert -> productsList.filter { product ->
                product.category == "Dessert"
            }
        }
    }
}