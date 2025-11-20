package com.example.stylishe_commerceapp.presentation.SettingScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.domain.model.UserProfile
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.AddressDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.PaymentDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.PersonalDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.ProfileComponent
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.SaveButton
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.SettingTopAppBar
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel

@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingVIewModel) {
    val context = LocalContext.current
    val settingState by settingViewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var upiId by remember { mutableStateOf("") }

    LaunchedEffect(settingState.saveSuccess) {
        if (settingState.saveSuccess) {
            if (name.isNotEmpty() && pinCode.isNotEmpty() && address.isNotEmpty() &&
                city.isNotEmpty() && state.isNotEmpty() &&
                country.isNotEmpty() && upiId.isNotEmpty()
            ) {
                Toast.makeText(context, "\uD83D\uDCBE Saved Successfully", Toast.LENGTH_SHORT).show()
                settingViewModel.resetSaveSuccess()
            } else {
                Toast.makeText(context, "⚠\uFE0F Please fill all the fields ❗❗", Toast.LENGTH_SHORT).show()
            }
        }

    }
    LaunchedEffect(settingState.error) {
        settingState.error?.let {
            Toast.makeText(context, settingState.error, Toast.LENGTH_SHORT).show()
            settingViewModel.clearError()
        }

    }

    LaunchedEffect(settingState.userProfile) {
        name = settingState.userProfile.name
        pinCode = settingState.userProfile.pinCode
        address = settingState.userProfile.address
        city = settingState.userProfile.city
        state = settingState.userProfile.state
        country = settingState.userProfile.country
        upiId = settingState.userProfile.upiId
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.WhiteSmoke),
        topBar = {
            SettingTopAppBar {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Column {
                        ProfileComponent {/*onClick*/ }
                        PersonalDetails(
                            name = name,
                            email = settingState.userProfile.email,
                            nameTextField = { name = it },
                            emailTextField = { }
                        )

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        )

                        AddressDetails(
                            pinCode = pinCode,
                            address = address,
                            city = city,
                            state = state,
                            country = country,
                            pinCodeValueChanged = { pinCode = it },
                            addressValueChanged = { address = it },
                            cityChanged = { city = it },
                            stateChanged = { state = it },
                            countryChanged = { country = it }
                        )

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        )

                        PaymentDetails(
                            upiId = upiId,

                            ) {
                            upiId = it
                        }
                        SaveButton(settingViewModel) {

                            val allFilled = name.isNotEmpty() &&
                                    pinCode.isNotEmpty() &&
                                    address.isNotEmpty() &&
                                    city.isNotEmpty() &&
                                    state.isNotEmpty() &&
                                    country.isNotEmpty() &&
                                    upiId.isNotEmpty()

                            val anyChanged = name != settingState.userProfile.name ||
                                    pinCode != settingState.userProfile.pinCode ||
                                    address != settingState.userProfile.address ||
                                    city != settingState.userProfile.city ||
                                    state != settingState.userProfile.state ||
                                    country != settingState.userProfile.country ||
                                    upiId != settingState.userProfile.upiId

                            if (!allFilled) {
                                Toast.makeText(context, "⚠\uFE0F Please fill all the fields ❗❗", Toast.LENGTH_SHORT).show()
                                return@SaveButton
                            }

                            if (!anyChanged) {
                                Toast.makeText(context, "⚡Already Saved", Toast.LENGTH_SHORT).show()
                                return@SaveButton
                            }

                            // Save data
                            val userProfile = UserProfile(
                                name = name,
                                email = settingState.userProfile.email,
                                address = address,
                                city = city,
                                state = state,
                                country = country,
                                upiId = upiId,
                                pinCode = pinCode
                            )
                            settingViewModel.updateUserProfile(userProfile)
                        }

                    }
                }

            }
        }
    }

}

