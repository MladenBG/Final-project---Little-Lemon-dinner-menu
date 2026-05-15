package com.littlelemon.menu

class SortHelper {

    // Default sorting based on title alphabetically
    private fun List<ProductItem>.sortAlphabetically(): List<ProductItem> {
        return this.sortedBy { it.title }
    }

    // Task 7: Add extension function for sorting based on price ascending
    private fun List<ProductItem>.sortBasedOnPriceAscending(): List<ProductItem> {
        return this.sortedBy { it.price }
    }

    // Task 7: Add extension function for sorting based on price descending
    private fun List<ProductItem>.sortBasedOnPriceDescending(): List<ProductItem> {
        return this.sortedByDescending { it.price }
    }

    // Task 7: Execute extension function calls based on the selected SortType
    fun sortProducts(type: SortType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            SortType.Alphabetically -> productsList.sortAlphabetically()
            SortType.PriceAsc -> productsList.sortBasedOnPriceAscending()
            SortType.PriceDesc -> productsList.sortBasedOnPriceDescending()
            else -> productsList.sortAlphabetically()
        }
    }
}