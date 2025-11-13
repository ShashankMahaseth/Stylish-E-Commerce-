package com.example.stylishe_commerceapp.presentation.Components.HomeComponents

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stylishe_commerceapp.R
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.delay

@Composable
@Preview(showSystemUi = true)
fun Banner() {
    val pagerState = rememberPagerState(pageCount = { BannerImage.image.size })
    LaunchedEffect(pagerState.pageCount) {
        while (true) {
            delay(3000) // delay between slides (3 seconds)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier

                .fillMaxWidth()
                .size(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        ) { page ->

            Image(
                painter = painterResource(id = BannerImage.image[page]),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

        }
        Spacer(modifier=Modifier.height(8.dp))
        DotsIndicator(
            dotCount = pagerState.pageCount,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    color = colorResource(R.color.HotPink),
                    size = 8.dp
                )
            ),
            pagerState = pagerState,

            )
    }
}

object BannerImage {
    val image = listOf(
        R.drawable.group_33726__1_,
        R.drawable.gemini_generated_image_b7ojbzb7ojbzb7oj__1_,
        R.drawable.delivery,
    )
}