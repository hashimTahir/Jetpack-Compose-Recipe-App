/*
 * Copyright (c) 2021/  1/ 2.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.HeartButtonState.H_ACTIVE
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.HeartButtonState.H_IDLE


object HeartAnimationDefinition {
    enum class HeartButtonState {
        H_IDLE, H_ACTIVE
    }

    val hHeartColorPropKey = ColorPropKey(label = "hHeartColor")
    val hHeartSizePropKey = DpPropKey(label = "hHeartDp")

    private val hIdleIconSize = 50.dp
    private val hExpandedIconSize = 80.dp

    val hHeartDefinition =
        transitionDefinition<HeartButtonState> {
            state(H_IDLE) {
                this[hHeartColorPropKey] = Color.LightGray
                this[hHeartSizePropKey] = hIdleIconSize
            }
            state(H_ACTIVE) {
                this[hHeartColorPropKey] = Color.Red
                this[hHeartSizePropKey] = hIdleIconSize
            }
            transition(
                H_IDLE to H_ACTIVE,
            ) {
                hHeartColorPropKey using tween(durationMillis = 500)
                hHeartSizePropKey using keyframes {
                    durationMillis = 500
                    hExpandedIconSize at 100
                    hIdleIconSize at 200
                }
            }


            transition(
                H_ACTIVE to H_IDLE,
            ) {
                hPulsePropKey using infiniteRepeatable(
                    animation = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            }
        }

}