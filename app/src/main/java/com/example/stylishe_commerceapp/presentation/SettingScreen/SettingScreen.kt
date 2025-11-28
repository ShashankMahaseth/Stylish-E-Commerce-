package com.example.stylishe_commerceapp.presentation.SettingScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.domain.model.UserProfile
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.AddressDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.PaymentDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.PersonalDetails
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.ProfileComponent
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.SaveButton
import com.example.stylishe_commerceapp.presentation.Components.SettingComponent.SettingTopAppBar
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.SettingVIewModel
import com.example.stylishe_commerceapp.presentation.common.FailureComponent
import com.example.stylishe_commerceapp.presentation.common.LoadingIndicator
import com.google.android.gms.auth.api.signin.GoogleSignIn

@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingVIewModel,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    val settingState by settingViewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var upiId by remember { mutableStateOf("") }
    var scroll by remember { mutableStateOf(true) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var popUpMessage by remember { mutableStateOf(false) }




    LaunchedEffect(settingState.userProfile.email) {
        if (settingState.userProfile.email.isNotEmpty()) {
            // Email is automatically loaded from Firebase Auth
        }
    }

    LaunchedEffect(settingState.saveSuccess) {
        if (settingState.saveSuccess) {
            if (name.isNotEmpty() && pinCode.isNotEmpty() && address.isNotEmpty() &&
                city.isNotEmpty() && state.isNotEmpty() &&
                country.isNotEmpty() && upiId.isNotEmpty()
            ) {
                Toast.makeText(context, "\uD83D\uDCBE Saved Successfully", Toast.LENGTH_SHORT)
                    .show()
                settingViewModel.resetSaveSuccess()
            } else {
                Toast.makeText(context, "⚠\uFE0F Please fill all the fields ❗❗", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
    LaunchedEffect(settingState.error) {
        settingState.error?.let {
            Toast.makeText(context, settingState.error, Toast.LENGTH_SHORT).show()
            settingViewModel.clearError()
        }

    }

    LaunchedEffect(Unit) {
        settingViewModel.loadUserData()
        settingViewModel.loadUserProfile()
    }


    LaunchedEffect(settingState.userProfile) {//whenever the userProfile from the  ViewModel changes this effect updates local remember text field so UI shows the saved values.
        name = settingState.userProfile.name
        pinCode = settingState.userProfile.pinCode
        address = settingState.userProfile.address
        city = settingState.userProfile.city
        state = settingState.userProfile.state
        country = settingState.userProfile.country
        upiId = settingState.userProfile.upiId
        //  email = settingState.userProfile.email

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
        when {
            settingState.isLoading -> {
                LoadingIndicator()
            }

            settingState.error != null -> {
                FailureComponent {
                    settingViewModel.loadUserData()
                    settingViewModel.loadUserProfile()
                }
            }

            else -> {


                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center


                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .alpha(if(!popUpMessage) alpha else 0.3f),
                        userScrollEnabled = scroll
                    ) {
                        item {
                            Column {
                                ProfileComponent(settingViewModel)
                                PersonalDetails(
                                    name = name,
                                    email = when (val account =
                                        GoogleSignIn.getLastSignedInAccount(context)) {
                                        null -> settingViewModel.firebaseAuth.currentUser?.email
                                            ?: ""

                                        else -> account.email
                                            ?: "Already LoggedIn with Google , Facebook etc."
                                    },
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
                                SaveButton(enabled = !popUpMessage,settingViewModel = settingViewModel) {


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
                                        Toast.makeText(
                                            context,
                                            "⚠\uFE0F Please fill all the fields ❗❗",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@SaveButton
                                    }

                                    if (!anyChanged) {
                                        Toast.makeText(
                                            context,
                                            "⚡Already Saved",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        return@SaveButton//this returns only from the lambda, not the whole composable).
                                    }

                                    // Save data
                                    val userProfile = UserProfile(
                                        name = name,
                                        email = when (val account =
                                            GoogleSignIn.getLastSignedInAccount(context)) {
                                            null -> settingViewModel.firebaseAuth.currentUser?.email
                                                ?: ""

                                            else -> account.email
                                                ?: "Already LoggedIn with Google , Facebook etc."
                                        },

                                        address = address,
                                        city = city,
                                        state = state,
                                        country = country,
                                        upiId = upiId,
                                        pinCode = pinCode
                                    )
                                    settingViewModel.updateUserProfile(userProfile)
                                }
                                Divider(modifier = Modifier.padding(32.dp))
                                Button(
                                    onClick = {
                                        scroll =false
                                        popUpMessage=true
                                    },
                                    colors = ButtonDefaults.textButtonColors(colorResource(R.color.Maroon)),
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = "Logout",
                                        color = Color.White
                                    )
                                }

                            }
                        }

                    }
                    if(popUpMessage) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.80f)
                                .height(250.dp)
                                .shadow(6.dp, shape = RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(colorResource(R.color.WhiteSmoke))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Text(
                                        text = "Confirm to Logout?",
                                        fontWeight = FontWeight.SemiBold,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.align(Alignment.CenterHorizontally),
                                        fontSize = 24.sp
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Button(
                                            onClick = {
                                                scroll = true
                                                popUpMessage=false
                                            },
                                            colors = ButtonDefaults.buttonColors(colorResource(R.color.DodgerBlue)),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = "Decline"
                                            )
                                        }
                                        Button(
                                            onClick = {
                                                authViewModel.logout()
                                               navController.navigate(Routes.Onboarding){
                                                   popUpTo(Routes.Splash) {
                                                       inclusive = true
                                                   }

                                               }
                                                Toast.makeText(context, "Logged Out Successfully", Toast.LENGTH_SHORT)
                                                    .show()

                                                      },
                                            colors = ButtonDefaults.buttonColors(colorResource(R.color.Red)),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = "Confirm"
                                            )
                                        }

                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

}

