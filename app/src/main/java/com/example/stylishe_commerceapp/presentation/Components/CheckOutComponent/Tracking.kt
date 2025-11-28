package com.example.stylishe_commerceapp.presentation.Components.CheckOutComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
fun Tracking(selectedIndex: Int) {
    val selectedIndex = selectedIndex
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),


            ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(times = 2) { times ->

                        Box(
                            Modifier
                                .weight(0.4f) // 50%
                                .height(1.dp)
                                .background(
                                    if (selectedIndex >= times + 1) {
                                        colorResource(R.color.Crimson)
                                    } else {
                                        colorResource(R.color.silver)
                                    }
                                )

                        )

                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    repeat(times = 3) { times ->
                        Column() {
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = if (selectedIndex >= times) colorResource(R.color.Crimson) else colorResource(
                                            R.color.silver
                                        ),
                                        shape = CircleShape
                                    )
                                    .size(8.dp)
                                    .border(
                                        0.4.dp,
                                        color = if (selectedIndex == times) colorResource(R.color.Snow) else if(selectedIndex>=times) colorResource(
                                            R.color.Crimson
                                        )else colorResource(R.color.silver),
                                        shape = CircleShape
                                    )
                            )


                        }

                    }
                }


            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            repeat(3) { it ->
                Text(
                    text = when (it) {
                        0 -> "delivery details"
                        1 -> "payment"
                        else -> "confirmation"
                    },
                    fontWeight = FontWeight.Medium,
                    color = if (selectedIndex >= it) colorResource(R.color.Crimson) else colorResource(
                        R.color.silver
                    )
                )
            }
        }
    }

}