package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onListClick: () -> Unit,
    settingVIewModel: SettingVIewModel = hiltViewModel(),
    onProfileClick: () -> Unit
) {
    val state by settingVIewModel.state.collectAsState()
    val profileUrl = state.profilePhotoUrl
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.logoipsum_255_1),
                contentDescription = "StylishLogo"
            )
        },
        navigationIcon = {
            IconButton(onClick = onListClick) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "list",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {

                IconButton(
                    onClick = onProfileClick,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(35.dp)


                ) {
                    AsyncImage(
                        model = profileUrl ?: R.drawable.profile,   // fallback image
                        contentDescription = "Profile",
                        modifier = Modifier.size(35.dp),
                        contentScale = ContentScale.Crop
                    )
                }


        },
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                spotColor = colorResource(R.color.black),
                ambientColor = colorResource(R.color.black)
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.LightPink)
        )
    )
}