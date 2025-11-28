package com.example.stylishe_commerceapp.presentation.Components.SettingComponent


import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent


@Composable
fun SaveButton(enabled: Boolean,settingViewModel: SettingVIewModel,onClicked:()-> Unit){
 val state by settingViewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(state.error) {
        if(state.error !=null){
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            settingViewModel.clearError()
        }
    }

    Button(
        onClick = onClicked,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
            .size(size=54.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(colorResource(R.color.Crimson)),
        enabled = enabled
    ) {
        if(state.isSaving){
            CircularProgressIndicator(color = Color.White)
        }else if(state.error !=null){

                settingViewModel.resetSaveSuccess()


        }
        else {
            Text(
                text = "Save"
            )
        }



    }
}