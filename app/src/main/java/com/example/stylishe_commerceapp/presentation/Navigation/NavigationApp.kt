package com.example.stylish.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.forgetpage.ForgotPage
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.loginpage.LoginScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.AuthPage.signuppage.SignUpScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.SplashScreen.SplashScreen
import com.example.stylishe_commerceapp.presentation.AuthPage.onboarding.OnBoardingScreen
import com.example.stylishe_commerceapp.presentation.HomePage.AllProductScreen
import com.example.stylishe_commerceapp.presentation.HomePage.HomeScreen
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.example.stylishe_commerceapp.presentation.AllCommonProductScreen.AllCommonProductScreen
import com.example.stylishe_commerceapp.presentation.Components.HomeComponents.HomePage
import com.example.stylishe_commerceapp.presentation.Favorite.FavoritePage
import com.example.stylishe_commerceapp.presentation.ProductDetailsScreen.ProductDetailsScreen
import com.example.stylishe_commerceapp.presentation.SearchScreen.SearchScreen
import com.example.stylishe_commerceapp.presentation.ViewModel.AuthViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.ProductViewModel
import com.example.stylishe_commerceapp.presentation.ViewModel.UserPreferencesViewModel

@Composable
fun NavigationApp() {
    val context = LocalContext.current


    val viewModel: AuthViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()

    val userPreferencesViewModel: UserPreferencesViewModel = viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable<Routes.Splash> {
            SplashScreen(navController, userPreferencesViewModel)
        }
        composable<Routes.Onboarding> {
            OnBoardingScreen(navController)
        }
        composable<Routes.Login> {
            LoginScreen(
                navController,
                viewModel,
            )
        }
        composable<Routes.SignUp> {
            SignUpScreen(navController, viewModel)
        }
        composable<Routes.Forgot> {
            ForgotPage(navController, viewModel)
        }
        composable<Routes.Home> {
            // Disable back button on HomeScreen
            //  BackHandler(enabled = true) { }

            HomePage(productViewModel,navController)
        }
        composable<Routes.AllProductScreen> {
            AllProductScreen(navController, productViewModel)
        }
        composable<Routes.SearchScreen> {
            SearchScreen(searchViewModel = productViewModel, navController)
        }
        composable<Routes.ProductDetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.ProductDetailScreen>()
            ProductDetailsScreen(
                viewModel = productViewModel,
                productId = args.productId,
                navController
            )
        }
        composable <Routes.AllCommonProductScreen>{  backStackEntry ->
            val args = backStackEntry.toRoute<Routes.AllCommonProductScreen>()
            AllCommonProductScreen(
                navController = navController,
                productViewModel = productViewModel,
                categoryName = args.categoryName
            )


        }
        composable<Routes.FavoritePage> {
            FavoritePage(navController)
        }
    }
}

