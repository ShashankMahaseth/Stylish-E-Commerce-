package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.stylishe_commerceapp.R


@Composable
fun ProductCard(thumbnail: String?, title: String?, price: Double?) {

  val context=LocalContext.current
    Card(
        shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .shadow(elevation = 3.dp,
                    shape = RoundedCornerShape(8.dp),
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    ).size(width = 200.dp, height = 250.dp),
                colors = CardDefaults
                    .cardColors(colorResource(R.color.Snow)),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model= ImageRequest
                            .Builder(context)
                            .data(thumbnail).build()
                        ,
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = title?:"no text",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "$price(50%off)",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = colorResource(R.color.green)
                    )
                    Text(
                        text = "$price",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = colorResource(R.color.Silver),
                        textDecoration = TextDecoration.LineThrough
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Rating()
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }


