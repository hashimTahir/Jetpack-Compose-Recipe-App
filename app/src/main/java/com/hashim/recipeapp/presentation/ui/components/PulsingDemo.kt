/*
 * Copyright (c) 2021/  1/ 2.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.presentation.ui.components.PulseAnimationDefinition.PulseState.H_FINAL
import com.hashim.recipeapp.presentation.ui.components.PulseAnimationDefinition.PulseState.H_INITIAL
import com.hashim.recipeapp.presentation.ui.components.PulseAnimationDefinition.hPulseDefination
import com.hashim.recipeapp.presentation.ui.components.PulseAnimationDefinition.hPulsePropKey
import timber.log.Timber


@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary
    val pulseAnim = transition(
        definition = hPulseDefination,
        initState = H_INITIAL,
        toState = H_FINAL
    )
    val pulseMagnitude = pulseAnim[hPulsePropKey]
    Timber.d("pulsingdemo %s", pulseMagnitude)

    Canvas(
        modifier = Modifier.fillMaxWidth()
            .height(55.dp),
        onDraw = {
            drawCircle(
                radius = pulseMagnitude,
                brush = SolidColor(color)
            )
        })
}

object PulseAnimationDefinition {
    enum class PulseState {
        H_INITIAL, H_FINAL
    }

    val hPulsePropKey = FloatPropKey("hPulseKey")

    val hPulseDefination =
        transitionDefinition<PulseState> {
            state(H_INITIAL) {
                this[hPulsePropKey] = 40f
            }
            state(H_FINAL) {
                this[hPulsePropKey] = 50f
            }
            transition(
                H_INITIAL to H_FINAL,
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