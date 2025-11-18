package com.example.stylishe_commerceapp.presentation.Components.SettingComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@Composable
fun SettingTextField(value:String,onValueChange:(String)->Unit,enabled:Boolean = false){

    OutlinedTextField(
        value=value,
        onValueChange =onValueChange,
        modifier = Modifier.fillMaxWidth().size(50.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor =  if(value.isEmpty()) colorResource(R.color.Red) else colorResource(R.color.Silver),
            focusedIndicatorColor = if(value.isEmpty()) colorResource(R.color.Red) else colorResource(R.color.Blue),
            focusedContainerColor = colorResource(R.color.WhiteSmoke),
            unfocusedContainerColor = colorResource(R.color.WhiteSmoke),
            cursorColor =  if(value.isEmpty()) colorResource(R.color.Red) else colorResource(R.color.Blue)
        ),
        singleLine = true,
        placeholder =   if(value.isEmpty()) {
                {
                    Text(
                        text = "Cannot Be Empty!!",
                        color = colorResource(R.color.Red),
                        fontSize = 12.sp,

                    )
                }
            } else null,
        enabled = enabled

    )

}