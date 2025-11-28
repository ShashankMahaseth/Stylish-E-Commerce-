package com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.AuthScreen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.core.utils.Result
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.AuthButton.AuthButton
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.LoginIcon.LoginIcon
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.TextField.TextFieldItem
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.forgetScreen.forgetPasswordButton.ForgetPasswordButton
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.forgetScreen.forgetText.ForgotText
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.registerButton.RegisterButton
import com.example.stylishe_commerceapp.presentation.Components.AuthComponents.topheadertext.TopHeaderText
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun AuthScreen(
    isSignUp: Boolean,
    authText: String,
    authText2: String,
    topText1: String,
    topText2: String,
    isForgot: Boolean,
    onNavForgetClick: () -> Unit,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    var username by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authState1 by authViewModel.authState.collectAsState()//Collect data from stateflow
    var errorMessage by remember { mutableStateOf("") }
    var isMatchedPassword by remember { mutableStateOf(false) }

    var isVisible by remember { mutableStateOf(false) }
    val passwordIcon = if (isVisible) R.drawable.ic_visibilty_on else R.drawable.ic_visibility_off

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),

        ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    if (account != null) {
                        authViewModel.signInWithGoogle(account)
                    } else {

                        errorMessage = "Google Sign In Failed"
                    }
                } catch (e: ApiException) {
                    Toast.makeText(context, "Google Sign In Failed", Toast.LENGTH_SHORT).show()
                    errorMessage = "Google sign in failed: ${e.statusCode}-${e.message}"
                }
            }

            Activity.RESULT_CANCELED -> {
                Toast.makeText(context, "Google Sign In Cancelled", Toast.LENGTH_SHORT).show()
                errorMessage = "Google Sign In Cancelled"
            }

            else -> {
                Toast.makeText(context, "Google Sign In Failed", Toast.LENGTH_SHORT).show()

            }

        }
    }

    // Observe authentication state
    LaunchedEffect(authState1) {
        when (authState1) {
            is Result.Success -> {
                if (!isSignUp) {
                    // Login successful  go Home
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Onboarding) { inclusive = true }
                    }
                } else {
                    // Signup successful go to Login
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.SignUp) { inclusive = true }
                    }

                }
                authViewModel.resetState()
            }

            is Result.Failure -> {
                errorMessage = (authState1 as Result.Failure).message
            }

            else -> {}
        }
    }
    Scaffold(containerColor = Color.White) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopHeaderText(text1 = topText1, text2 = topText2)
            TextFieldItem(
                labelName = "Email or Username",
                onValueChanged = { username = it },
                value = username,
                leadingIcon = R.drawable.user,
                visualTransformation = VisualTransformation.None
            )
            if (isForgot) {
                ForgotText()
            }
            if (!isForgot) {
                TextFieldItem(
                    labelName = "password",
                    onValueChanged = { inputPassword = it },
                    value = inputPassword,
                    leadingIcon = R.drawable.group_2,
                    trailingIcon = {
                        IconButton(
                            onClick = { isVisible = !isVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Image(
                                painter = painterResource(passwordIcon),
                                contentDescription = "Visibility icon"
                            )
                        }
                    },
                    visualTransformation = if (!isVisible) PasswordVisualTransformation() else VisualTransformation.None
                )
                if (isSignUp) {
                    TextFieldItem(
                        labelName = "Confirm password",
                        onValueChanged = { confirmPassword = it },
                        value = confirmPassword,
                        leadingIcon = R.drawable.group_2,
                        trailingIcon = {
                            IconButton(
                                onClick = { isVisible = !isVisible },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Image(
                                    painter = painterResource(passwordIcon),
                                    contentDescription = "Visibility icon",
                                )
                            }
                        },
                        visualTransformation = if (!isVisible) PasswordVisualTransformation() else VisualTransformation.None
                    )
                    if (inputPassword != confirmPassword) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Password not Matched!!",
                                fontSize = 12.sp,
                                color = Color.Red
                            )

                        }

                    }
                }


                if (isSignUp) {

                    RegisterButton()
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ForgetPasswordButton(onClicked = { navController.navigate(Routes.Forgot) })
                    }

                }
            }
            if (errorMessage.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                AuthButton(
                    onClicked = {

                        if (!isSignUp) {
                            if (username.isNotBlank() && inputPassword.isNotBlank()) {
                                authViewModel.login(username, inputPassword)
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            }
                        } else {

                            if (inputPassword == confirmPassword) {
                                if (username.isNotBlank() && inputPassword.isNotBlank() && confirmPassword.isNotBlank()) {
                                    authViewModel.signUp(username, inputPassword)
                                    Toast.makeText(context, "SignUp Successful", Toast.LENGTH_SHORT).show()
                                }
                            } else {

                                isMatchedPassword = !isMatchedPassword

                            }

                        }
                    }, text = authText,
                    authViewModel
                )
            }



            if (!isForgot) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "-Or Continue With-"
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    LoginIcon(
                        onClicked = { iconId ->
                            if (iconId == R.drawable.google) {


                                val gso =
                                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestIdToken(context.getString(R.string.default_web_client_id))
                                        .build()

                                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                                val signInIntent = googleSignInClient.signInIntent
                                googleSignInLauncher.launch(signInIntent)
                            }


                        },
                        "Login"
                    )
                }



                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Create An Account"
                    )
                    TextButton(
                        onClick = {
                            if (!isSignUp) navController.navigate(Routes.SignUp) {
                                popUpTo(Routes.SignUp) { inclusive = true }
                            }
                            else navController.navigate(Routes.Login)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = colorResource(
                                R.color.Crimson
                            )
                        )
                    ) {
                        Text(
                            text = authText2,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }


            }

        }
    }
}