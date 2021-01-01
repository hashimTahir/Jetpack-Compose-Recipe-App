/*
 * Copyright (c) 2020/  12/ 31.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    isDisplayed: Boolean,
) {

    if (isDisplayed) {

        WithConstraints(modifier = Modifier.fillMaxWidth()) {
            val hConstraint = if (minWidth < 600.dp) {
                hDecoupledConstraints(0.3F)
            } else {
                hDecoupledConstraints(0.7F)
            }

            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                constraintSet = hConstraint
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.layoutId("progressbar"),
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = "Loading...",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = TextUnit.Companion.Sp(15)
                    ),
                    modifier = Modifier.layoutId("text"),
                )

            }

        }

    }


}

private fun hDecoupledConstraints(
    verticalBias: Float
): ConstraintSet {
    return ConstraintSet {
        val hGuideline = createGuidelineFromTop(verticalBias)
        val hProgressbar = createRefFor("progressbar")
        val hText = createRefFor("text")
        constrain(hProgressbar) {
            top.linkTo(hGuideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(hText) {
            top.linkTo(hProgressbar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    }
}