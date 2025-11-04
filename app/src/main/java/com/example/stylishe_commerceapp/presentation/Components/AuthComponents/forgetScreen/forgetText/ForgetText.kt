package com.example.stylishe_commerceapp.presentation.Components.AuthComponents.forgetScreen.forgetText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
@Preview(showSystemUi = true)
fun ForgotText(){
Row(
modifier = Modifier.fillMaxWidth().padding(16.dp),
verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Text(
        text ="*",
        color = colorResource(R.color.Crimson),
        modifier = Modifier.align(Alignment.Top)

    )
    Spacer(modifier = Modifier.width(2.dp))
    Text("We will send you a message to set or reset your new password",
        modifier = Modifier.align(Alignment.CenterVertically)
    )
}
}