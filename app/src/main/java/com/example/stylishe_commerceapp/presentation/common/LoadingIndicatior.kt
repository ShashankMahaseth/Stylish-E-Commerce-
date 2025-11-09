package com.example.stylishe_commerceapp.presentation.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.stylishe_commerceapp.R


@Composable
fun LoadingIndicator() {
    val colors = listOf(colorResource(R.color.Red),
        colorResource(R.color.yellow),
        colorResource(R.color.green),
        colorResource(R.color.blue))
    var currentColorIndex by remember { mutableStateOf(0) }

    // Infinite transition for smooth color change
    val infiniteTransition = rememberInfiniteTransition()
    val animatedColor by infiniteTransition.animateColor(
        initialValue = colors[currentColorIndex],
        targetValue = colors[(currentColorIndex + 1) % colors.size],
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), // 1 second smooth transition
            repeatMode = RepeatMode.Restart
        )
    )

    // Update index when animation completes
    LaunchedEffect(animatedColor) {
        delay(800) // match duration of tween
        currentColorIndex = (currentColorIndex + 1) % colors.size
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = animatedColor,
            modifier = Modifier,
            )
    }
}
