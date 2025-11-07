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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R

@Composable
@Preview(showSystemUi = true)
fun BottomNavigationBar(){




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
                                onClick = {}, modifier = Modifier.size(60.dp)
                                    .align(Alignment.Top).shadow(6.dp, shape = CircleShape),
                                colors = IconButtonDefaults.iconButtonColors(colorResource(R.color.Snow)),
                                shape = CircleShape
                            ) {

                                Icon(
                                    painter = painterResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }


                    }else{
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
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