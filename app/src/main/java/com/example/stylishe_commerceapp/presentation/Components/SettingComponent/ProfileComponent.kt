package com.example.stylishe_commerceapp.presentation.Components.SettingComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
fun ProfileComponent(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .padding(16.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                IconButton(
                    onClick = onClick,

                    colors = IconButtonDefaults.iconButtonColors
                        (
                        containerColor = colorResource(R.color.Blue)
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .border(
                            2.dp, color = colorResource(R.color.WhiteSmoke),
                            shape = CircleShape
                        )
                        .size(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = colorResource(R.color.White),

                        )
                }
            }

        }

    }
}
