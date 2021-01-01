/*
 * Copyright (c) 2020/  12/ 31.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.ConstraintSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CircularProgressBar(
    isDisplayed: Boolean,
) {

    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {

            val hProgressBar = createRef()
            val hTopGuideline = createGuidelineFromTop(0.3F)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(hProgressBar) {

                    top.linkTo(hTopGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colors.primary
            )

        }


    }


}