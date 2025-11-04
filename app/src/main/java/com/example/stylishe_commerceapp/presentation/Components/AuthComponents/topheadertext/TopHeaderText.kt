package com.example.stylishe_commerceapp.presentation.Components.AuthComponents.topheadertext

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TopHeaderText(text1:String,text2: String) {
    Text(
        text =text1,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        text=text2,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
}