package com.example.stylishe_commerceapp.presentation.Navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Splash: Routes()
    @Serializable
    data object Onboarding : Routes()

    @Serializable
    data object Login:Routes()
    @Serializable
    data object SignUp:Routes()

    @Serializable
    data object Forgot:Routes()

    @Serializable
    data object Home: Routes()



    @Serializable
    data object AllProductScreen:Routes()

    @Serializable
    data object SearchScreen: Routes()

    @Serializable
    data class ProductDetailScreen(val productId:Int?) : Routes()

    @Serializable
    data class AllCommonProductScreen(val categoryName: List< String>) : Routes()

    @Serializable
    data object FavoritePage : Routes()

    @Serializable
    data object  CategoryScreen : Routes()

    @Serializable
    data object SettingScreen : Routes()

    @Serializable
    data object CartScreen : Routes()

    @Serializable
    data object DeliveryScreen : Routes()

    @Serializable
    data object PaymentScreen : Routes()

    @Serializable
    data class ConformationScreen(val status: String?) : Routes()

}




