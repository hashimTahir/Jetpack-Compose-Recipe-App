/*
 * Copyright (c) 2021/  1/ 5.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components.utils

import androidx.compose.animation.core.*
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination.AnimationState.H_END
import com.hashim.recipeapp.presentation.ui.components.utils.ShimmerAnimationDefination.AnimationState.H_START

class ShimmerAnimationDefination(
    private val widthPx: Float,
    private val heightPx: Float,
    private val gradientWidth: Float
) {
    enum class AnimationState {
        H_START, H_END
    }

    val xShimmerPropKey = FloatPropKey(label = "xShimmerPropKey")
    val yShimmerPropKey = FloatPropKey(label = "yShimmerPropKey")


    val hShimmerTransitionDefination =
        transitionDefinition<AnimationState> {
            state(H_START) {
                this[xShimmerPropKey] = 0f
                this[yShimmerPropKey] = 0f
            }
            state(H_END) {
                this[xShimmerPropKey] = widthPx + gradientWidth
                this[yShimmerPropKey] = heightPx + gradientWidth
            }
            transition(H_START to H_END) {
                xShimmerPropKey using infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1300,
                        delayMillis = 300,
                        easing = LinearEasing

                    )
                )

                yShimmerPropKey using infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1300,
                        delayMillis = 300,
                        easing = LinearEasing
                    )
                )
            }
        }
}