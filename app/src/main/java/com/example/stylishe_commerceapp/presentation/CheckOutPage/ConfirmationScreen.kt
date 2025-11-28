package com.example.stylishe_commerceapp.presentation.CheckOutPage

import android.R.attr.visible
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes

@Composable
fun ConfirmationScreen(navController: NavController,status: String) {
    var visible by remember { mutableStateOf(false) }
    val scaleAnim by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    val image = if (status == "success") {
        R.drawable.star_1__2_
    } else {
        R.drawable.paymentfailed
    }
    BackHandler{    // Handle device back button for go to CartScreen

        navController.navigate(Routes.CartScreen)
    }
    LaunchedEffect(Unit) {
        visible = true
    }

    CheckOutPage(
        alpha = 0.01f,
        text = "confirmation",
        buttonText = "confirm",
        onClicked = {},
        selectedIndex = 2,
        navController = rememberNavController(),
        isBottomBar = false
    ) {


Box(modifier = Modifier.scale(scaleAnim)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
            .shadow(
                4.dp,
                RoundedCornerShape(6.dp)
            ),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(colorResource(R.color.Snow))
    ) {

        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Box {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp),
                        colorFilter = ColorFilter.tint( if(status=="success")colorResource(R.color.Crimson) else colorResource(R.color.Red))
                    )
                    if (status == "success") {
                        Image(
                            painter = painterResource(R.drawable.vector_5__1_),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Text(
                    text = if (status == "success") "Payment Successful" else " Payment Failed",
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (status == "success") {
                            navController.navigate(Routes.Home)
                        } else {
                            navController.navigate(Routes.PaymentScreen)
                        }
                    }, modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = colorResource(R.color.Crimson)
                        )
                ) {
                    Text(
                        text = "OK",
                    )
                }
            }

        }


    }
}


    }


}
