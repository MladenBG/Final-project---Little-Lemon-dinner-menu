package com.littlelemon.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text // Changed from material3 to material to fix the unresolved reference
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// Add a second parameter named startProductActivity in the ProductsGrid function declaration
@Composable
fun ProductsGrid(
    products: Products,
    startProductActivity: (ProductItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(80.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.items) { productItem ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    // In the Modifier.clickable body, execute this function and pass the ProductItem variable
                    .clickable {
                        startProductActivity(productItem)
                    }
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = productItem.image),
                    contentDescription = productItem.title,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = productItem.title)
                Text(text = "$${productItem.price}")
            }
        }
    }
}