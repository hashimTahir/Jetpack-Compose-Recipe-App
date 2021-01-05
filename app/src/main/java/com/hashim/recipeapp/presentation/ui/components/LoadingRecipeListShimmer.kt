/*
 * Copyright (c) 2021/  1/ 5.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination.AnimationState.H_END
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination.AnimationState.H_START

@Composable
fun LoadingRecipeListShimmer(
    cardHeigt: Dp,
    paddding: Dp = 16.dp
) {

    WithConstraints(
    ) {
        val cardWidthPx = with(AmbientDensity.current) {
            (maxWidth - (paddding * 2)).toPx()
        }
        val cardHeightPx = with(AmbientDensity.current) {
            (cardHeigt - (paddding)).toPx()
        }
        val hCardAnimationDefination = remember {
            ShimmerAnimationDefination(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx
            )

        }
        val hCardShimmerTranslateDefination = transition(
            definition = hCardAnimationDefination.hShimmerTransitionDefination,
            initState = H_START,
            toState = H_END
        )

        val hColors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.9f),
        )

        val hXCardShimmer =
            hCardShimmerTranslateDefination[hCardAnimationDefination.xShimmerPropKey]
        val hYCardShimmer =
            hCardShimmerTranslateDefination[hCardAnimationDefination.yShimmerPropKey]

        ShimmerRecipeCardItem(
            colors = hColors,
            cardHeight = cardHeigt,
            xShimmer = hXCardShimmer,
            yShimmer = hYCardShimmer
        )
    }
}
