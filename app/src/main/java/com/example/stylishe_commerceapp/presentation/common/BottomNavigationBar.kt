package com.example.stylishe_commerceapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes

@Composable
fun BottomNavigationBar(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()//it remember the navigation state
    val currentRoute = navBackStackEntry?.destination?.route//store navigation process



        BottomAppBar(
            containerColor = colorResource(R.color.ic_launcher_background),
            modifier = Modifier.shadow(4.dp)
        ) {




            Row(
                modifier = Modifier.fillMaxSize()
                   ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavigationBaIcon.icons.forEachIndexed {index,icon->

                    if(index==2) {
                        IconButton(
                                onClick = {
                                     navController.navigate(Routes.CartScreen)

                                }, modifier = Modifier.size(60.dp)
                                    .align(Alignment.Top).shadow(6.dp, shape = CircleShape),
                                colors =
                                    IconButtonDefaults.iconButtonColors(
                                        containerColor = when{
                                            currentRoute == Routes.CartScreen::class.qualifiedName->colorResource(R.color.Crimson)
                                            else->colorResource(R.color.Snow)
                                        }
                                    ),
                                shape = CircleShape
                            ) {

                                Icon(
                                    painter = painterResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                    tint = when{
                                        currentRoute == Routes.CartScreen::class.qualifiedName->colorResource(R.color.Snow)
                                        else->colorResource(R.color.Black)
                                    }
                                )
                            }


                    }else{
                        IconButton(onClick = {
                            when(index) {
                                0 -> navController.navigate(Routes.Home)
                                1-> navController.navigate(Routes.FavoritePage)
                                3-> navController.navigate(Routes.CategoryScreen)
                                4->navController.navigate(Routes.SettingScreen)


                            }

                        }) {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp),
                                tint = when {
                                    index == 0 && currentRoute == Routes.Home::class.qualifiedName -> colorResource(R.color.Crimson)
                                    index == 1 && currentRoute == Routes.FavoritePage::class.qualifiedName -> colorResource(R.color.Crimson)
                                    index == 3 && currentRoute == Routes.CategoryScreen::class.qualifiedName -> colorResource(R.color.Crimson)
                                    index==4 && currentRoute == Routes.SettingScreen::class.qualifiedName->colorResource(R.color.Crimson)
                                    else -> Color.Black
                                }
                            )
                        }
                    }


                }

            }

        }

}

object BottomNavigationBaIcon{
    val icons =listOf(
        R.drawable.home,
        R.drawable.heart,
        R.drawable.shopping_cart,
        R.drawable.menu,
        R.drawable.setting

    )

}