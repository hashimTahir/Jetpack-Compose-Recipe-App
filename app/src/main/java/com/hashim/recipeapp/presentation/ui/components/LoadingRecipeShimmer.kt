/*
 * Copyright (c) 2021/  1/ 21.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination


@Composable
fun LoadingRecipeShimmer(
    cardHeigt: Dp,
    paddding: Dp = 16.dp
) {

    WithConstraints {
        val cardWidthPx = with(AmbientDensity.current) {
            (maxWidth - (paddding * 2)).toPx()
        }
        val cardHeightPx = with(AmbientDensity.current) {
            (cardHeigt - (paddding)).toPx()
        }
        val hCardAnimationDefination = remember {
            ShimmerAnimationDefination(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx,
            )
        }
        val hCardShimmerTranslateDefination = transition(
            definition = hCardAnimationDefination.hShimmerTransitionDefination,
            initState = ShimmerAnimationDefination.AnimationState.H_START,
            toState = ShimmerAnimationDefination.AnimationState.H_END
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
        ScrollableColumn {
            val hBrush = Brush.linearGradient(
                hColors,
                start = Offset(
                    hXCardShimmer - hCardAnimationDefination.gradientWidth,
                    hYCardShimmer - hCardAnimationDefination.gradientWidth
                ),
                end = Offset(hXCardShimmer, hYCardShimmer)
            )

            Column(modifier = Modifier.padding(paddding)) {
                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(cardHeigt )
                            .background(brush = hBrush)
                    )
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(cardHeigt / 10)
                            .background(brush = hBrush)
                    )
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Surface(shape = MaterialTheme.shapes.small) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(cardHeigt / 10)
                            .background(brush = hBrush)
                    )
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }
}
