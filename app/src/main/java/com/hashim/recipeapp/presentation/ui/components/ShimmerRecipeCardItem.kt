/*
 * Copyright (c) 2021/  1/ 5.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    cardHeight: Dp,
    xShimmer: Float,
    yShimmer: Float
) {
    val hBrush = Brush.linearGradient(
        colors,
        start = Offset(xShimmer, yShimmer),
        end = Offset(xShimmer + 200, yShimmer + 200)
    )
    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier.fillMaxWidth()
                .preferredHeight(cardHeight)
                .background(brush = hBrush)
        )

    }
}