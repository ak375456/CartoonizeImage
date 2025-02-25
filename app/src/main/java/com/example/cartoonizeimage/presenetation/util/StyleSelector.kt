package com.example.cartoonizeimage.presenetation.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.cartoonizeimage.util.CartoonStyle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun StyleSelector(
    selectedStyle: CartoonStyle,
    onStyleSelected: (CartoonStyle) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = "Select Style: ${selectedStyle.value}")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            CartoonStyle.values().forEach { style ->
                DropdownMenuItem(
                    text = { Text(style.name) },
                    onClick = {
                        onStyleSelected(style)
                        expanded = false
                    }
                )
            }
        }
    }
}