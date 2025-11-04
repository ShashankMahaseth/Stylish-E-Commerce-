package com.example.stylishe_commerceapp.presentation.Components.AuthComponents.TextField

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
@Composable
fun TextFieldItem(
    labelName: String,
    onValueChanged: (String) -> Unit, value: String,
    leadingIcon: Int,
     trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            label = {
                Text(
                    text = labelName
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(
                    R.color.Crimson
                ),
                unfocusedIndicatorColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedLabelColor = colorResource(R.color.Crimson)
            ),
            leadingIcon = {
                Image(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = trailingIcon
            ,
            singleLine = true,
            visualTransformation = visualTransformation
        )
    }

}