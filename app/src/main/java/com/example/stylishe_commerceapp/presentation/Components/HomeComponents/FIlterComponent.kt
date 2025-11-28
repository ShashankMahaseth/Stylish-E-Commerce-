package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
fun FilterComponent(
    onFilterSelected: (String) -> Unit,
    onSortSelected: (String) -> Unit
) {
    val filterList = listOf("beauty", "fashion", "electronics","women's","mens")
    val short  = listOf("low to high","high to low")
    var expanded by remember { mutableStateOf(false) }
    var expanded1 by remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.Absolute.Right
    ) {
        Box {
            TextButton(
                onClick = {
                    expanded=true
                          },

                ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.FilterAlt,
                        contentDescription = null,
                        tint = colorResource(R.color.Crimson),

                        )
                    Text(
                        text = "Filter",
                        color = colorResource(R.color.Crimson),
                    )
                }

            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded=false}
            ) {
                filterList.forEach {text ->
                    DropdownMenuItem(
                        text = {
                            Text(text = text)
                        },
                        onClick = {
                            expanded = false
                            onFilterSelected(text.lowercase())
                        }
                    )
                }


            }
        }
        Box {
            TextButton(
                onClick = {expanded1=true},

                ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.SwapVert,
                        contentDescription = null,
                        tint = colorResource(R.color.Crimson),

                        )
                    Text(
                        text = "Short",
                        color = colorResource(R.color.Crimson),
                    )
                }

            }
            DropdownMenu(
                expanded = expanded1,
                onDismissRequest = {expanded1=false}
            ) {
                short.forEach {text ->
                    DropdownMenuItem(
                        text = {
                            Text(text = text)
                        },
                        onClick = {
                            expanded1 = false
                           onSortSelected(text)
                        }
                    )
                }


            }
        }

    }
}