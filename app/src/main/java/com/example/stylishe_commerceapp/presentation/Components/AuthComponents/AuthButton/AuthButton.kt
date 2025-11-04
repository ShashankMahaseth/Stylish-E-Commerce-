package com.example.stylishe_commerceapp.presentation.Components.AuthComponents.AuthButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel


@Composable
fun AuthButton(onClicked:()-> Unit,text: String,authViewModel: AuthViewModel){
    val authState by authViewModel.authState.collectAsState()


    Button(
        onClick = onClicked,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            .size(size=54.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.Crimson))
    ) {
        if(authState is Result.Loading){


                CircularProgressIndicator(
                    color = Color.White
                )

        }else{
            Text(
                text = text
            )

        }


    }
}