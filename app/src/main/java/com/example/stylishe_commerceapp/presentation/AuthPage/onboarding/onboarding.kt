package com.example.stylishe_commerceapp.presentation.AuthPage.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stylishe_commerceapp.R
import com.example.stylishe_commerceapp.presentation.Navigation.Routes
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType


import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { ListItems.image.size})
    val coroutineScope = rememberCoroutineScope()

    Scaffold (containerColor = Color.White) { innerPadding->
        Column(modifier= Modifier.fillMaxSize()
            .padding(innerPadding)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(modifier =Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row{
                    Text("${pagerState.currentPage+1}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text("/${pagerState.pageCount}", fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.LightGray
                    )
                }
                TextButton(onClick = {navController.navigate(Routes.Login)}) {
                    Text(
                        "Skip", fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorResource(R.color.black)
                    )
                }

            }
            HorizontalPager(
                state = pagerState,
              modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                
                ){page->

                Column (modifier=Modifier.fillMaxWidth()
                    , horizontalAlignment = Alignment.CenterHorizontally){

                        Image(
                            painter = painterResource(ListItems.image[page]),
                            contentDescription = null, modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Text(
                            text = stringResource(ListItems.TextBold[page]),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                        Text(
                            text = stringResource(ListItems.textSemiBold),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 16  .dp)
                        )
                    }



            }
            Box {
                DotsIndicator(
                    dotCount = pagerState.pageCount,
                    type = ShiftIndicatorType(
                        dotsGraphic = DotGraphic(
                            color = colorResource(R.color.Crimson),
                            size = 8.dp
                        )
                    ),
                    pagerState = pagerState,

                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val prevPage =pagerState.currentPage-1

                    TextButton(onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page = prevPage)
                        }
                    }) {
                        Text(
                            "Prev", fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = if(pagerState.currentPage>0){
                                Color.Black
                            }else{
                                Color.LightGray
                            }
                        )

                    }
                       val nextPage =pagerState.currentPage+1
                    TextButton(onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage == pagerState.pageCount - 1) {
                              navController.navigate(Routes.Login)
                            } else {
                                pagerState.animateScrollToPage(page = nextPage)
                            }
                        }

                    }) {
                        Text(
                            text =if(pagerState.currentPage==pagerState.pageCount-1){
                                "Get Started"
                            }else{
                                "Next"
                            }
                            ,

                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = colorResource(R.color.black)
                        )

                    }

                }
            }

        }

    }

}