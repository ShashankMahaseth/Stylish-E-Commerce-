package com.example.stylishe_commerceapp.presentation.Components.AuthComponents.LoginIcon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
fun LoginIcon(onClicked: (Int) -> Unit, text: String) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        LoginIconImage.loginIcon.forEach { it ->
            var rotated by remember { mutableStateOf(false) }
            val rotation by animateFloatAsState(
                targetValue = if (rotated) 360f else 0f,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            IconButton(
                onClick ={onClicked(it)}
                , modifier = Modifier.size(88.dp)
            ) {

                Image(
                    painter = painterResource(it),
                    contentDescription = text,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)

                )
            }

        }
    }
}

object LoginIconImage {
    val loginIcon = listOf(
        R.drawable.google,
        R.drawable.apple,
        R.drawable.facebook
    )
}

