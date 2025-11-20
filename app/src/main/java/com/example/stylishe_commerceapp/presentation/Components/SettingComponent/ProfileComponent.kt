package com.example.stylishe_commerceapp.presentation.Components.SettingComponent

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel

@Composable
fun ProfileComponent( settingVIewModel: SettingVIewModel) {
    val state by settingVIewModel.state.collectAsState()
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    //Gallery launcher
    val galleryLauncher =rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri:Uri?->
        uri?.let {
            selectedImageUri = it

            Toast.makeText(context, "Image Selected", Toast.LENGTH_SHORT).show()

        }

    }
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
                when {
                    state.profilePhotoUrl != null -> {
                        // Show Google profile photo
                        AsyncImage(
                            model = state.profilePhotoUrl,
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFFE0E0E0), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    selectedImageUri != null -> {
                        // Show selected image from gallery
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFFE0E0E0), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    else -> {
                        // Show default avatar
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFFE0E0E0), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                IconButton(
                    onClick = {
                        galleryLauncher.launch("image/*")

                    },

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
