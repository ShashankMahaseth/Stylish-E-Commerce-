package com.example.stylishe_commerceapp.presentation.AuthPage.components.registerButton

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
@Composable
fun RegisterButton() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text("By clicking the")
        TextButton(onClick = {}) {
            Text(
                "Register",
                color = colorResource(R.color.Crimson),

                )
        }

        Text(
            "button,you agree to the offer",
            modifier = Modifier
        )


    }
}
