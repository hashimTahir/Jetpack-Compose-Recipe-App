/*
 * Copyright (c) 2021/  1/ 2.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.R
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.HeartButtonState.H_ACTIVE
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.HeartButtonState.H_IDLE
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.hHeartDefinition
import com.hashim.recipeapp.presentation.ui.components.HeartAnimationDefinition.hHeartSizePropKey
import com.hashim.recipeapp.utils.hLoadDrawable

@Composable
fun AnimationHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
    onToggle: () -> Unit,
) {
    val toState = if (buttonState.value == H_IDLE) {
        H_ACTIVE
    } else {
        H_IDLE
    }
    val state =
        transition(definition = hHeartDefinition, toState = toState, initState = buttonState.value)
    HeartButton(
        modifier = modifier,
        buttonState = buttonState,
        state = state,
        onToggle = onToggle
    )
}


@Composable
private fun HeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartAnimationDefinition.HeartButtonState>,
    state: TransitionState,
    onToggle: () -> Unit
) {

    if (buttonState.value == H_ACTIVE) {
        hLoadDrawable(drawable = R.drawable.heart_red).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = modifier.clickable(onClick = onToggle, indication = null)
                    .width(state[hHeartSizePropKey])
                    .height(state[hHeartSizePropKey])
            )
        }
    } else {
        hLoadDrawable(drawable = R.drawable.heart_grey).value?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = modifier.clickable(onClick = onToggle, indication = null)
                    .width(state[hHeartSizePropKey])
                    .height(state[hHeartSizePropKey])
            )
        }
    }
}

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
                hHeartColorPropKey using tween(durationMillis = 500)
                hHeartSizePropKey using keyframes {
                    durationMillis = 500
                    hExpandedIconSize at 100
                    hIdleIconSize at 200
                }
            }
        }

}