package com.example.stylishe_commerceapp.presentation.Components.SettingComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylishe_commerceapp.R

@Composable
fun PersonalDetails(
    name: String,
    email: String,
    nameTextField: (String) -> Unit,
    emailTextField: (String) -> Unit
) {
    var enable by remember { mutableStateOf(false) }


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Personal Details",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 16.dp).align(Alignment.CenterVertically)
                )
                Button(
                    onClick = {
                        enable=!enable
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.Crimson))
                ) {
                    Row {
                        Text(text = "Edit")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = colorResource(R.color.White)
                        )
                    }
                }
            }
            Text(text = "Name", color = colorResource(R.color.Silver))
            SettingTextField(value = name, onValueChange = nameTextField,enabled = enable)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "email", color = colorResource(R.color.Silver))
            SettingTextField(value = email, emailTextField)

        }

    }

}