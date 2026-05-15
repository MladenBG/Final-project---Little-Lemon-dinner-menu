package com.littlelemon.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text // Changed from material3 to material to fix the import error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// Composable function to display product details UI
@Composable
fun ProductDetails(productItem: ProductItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = productItem.image),
            contentDescription = productItem.title,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Title: ${productItem.title}")
        Text(text = "Price: $${productItem.price}")
        Text(text = "Category: ${productItem.category}")
    }
}