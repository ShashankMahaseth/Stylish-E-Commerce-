package com.example.stylishe_commerceapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@Composable
fun FailureComponent(onButtonClicked:()->Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.nointernet),
                contentDescription = null,
                modifier = Modifier.size(350.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onButtonClicked, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .size(52.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.
                buttonColors(containerColor = colorResource(R.color.Crimson))
            ) {
                Text(
                    text = "Retry",
                    fontSize = 18.sp
                )

            }
        }

    }

}