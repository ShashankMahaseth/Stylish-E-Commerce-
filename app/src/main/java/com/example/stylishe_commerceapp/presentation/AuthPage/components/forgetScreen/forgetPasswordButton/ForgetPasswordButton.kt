package com.example.stylishe_commerceapp.presentation.AuthPage.components.forgetScreen.forgetPasswordButton

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@Composable
fun ForgetPasswordButton(onClicked:()-> Unit){

    TextButton(onClick =onClicked) {
        Text(
            text ="Forgot Password?",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = colorResource(R.color.Crimson),

        )
    }
}