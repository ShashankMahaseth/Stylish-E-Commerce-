package com.example.stylishe_commerceapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.stylish.Navigation.NavigationApp
import com.example.stylishe_commerceapp.ui.theme.StylishECommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationApp()
        }
    }
}

