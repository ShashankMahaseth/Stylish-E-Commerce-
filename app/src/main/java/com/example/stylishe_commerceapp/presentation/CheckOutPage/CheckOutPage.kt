package com.example.stylishe_commerceapp.presentation.CheckOutPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Components.CheckOutComponent.PaymentTopAppBar
import com.example.stylishe_commerceapp.presentation.Components.CheckOutComponent.Tracking
import com.example.stylishe_commerceapp.presentation.Navigation.Routes

@Composable
fun CheckOutPage(
    alpha:Float,
    text: String,
    buttonText: String,
    onClicked: () -> Unit,
    selectedIndex: Int,
    navController: NavController,
    isBottomBar: Boolean=true,
    checkout: @Composable () -> Unit,
    ) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            PaymentTopAppBar {
                navController.popBackStack()

            }
        },

        bottomBar = {
            if (isBottomBar) {
                BottomAppBar(
                    actions = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = text, fontSize = 20.sp)
                            Button(
                                onClick = onClicked,
                                shape = RoundedCornerShape(topEnd = 8.dp, bottomStart = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        R.color.Crimson
                                    )
                                )
                            ) {
                                Text(
                                    text = buttonText,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                    },
                    modifier = Modifier.shadow(16.dp)

                )
            }



        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.SkyBlue),
                            colorResource(R.color.LightBlue),
                            colorResource(R.color.LightPink)
                        )
                    ),
                    alpha = alpha
                )
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier) {
                item {
                    Tracking(selectedIndex = selectedIndex)
                    Spacer(modifier = Modifier.height(32.dp))
                    checkout()
                }
            }


        }
    }
}