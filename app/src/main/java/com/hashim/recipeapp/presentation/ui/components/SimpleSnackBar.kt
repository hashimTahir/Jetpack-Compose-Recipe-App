/*
 * Copyright (c) 2021/  1/ 10.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleSnackBar(
    isShowing :Boolean,
    onHideSnackBar:()->Unit
) {
    if (isShowing) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val hSnackBar = createRef()
            Snackbar(
                modifier = Modifier.constrainAs(hSnackBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                action = {
                    Text(
                        text = "Hide",
                        modifier = Modifier.clickable(
                            onClick = onHideSnackBar,
                        ),
                        style = MaterialTheme.typography.h5
                    )
                }
            ) {
                Text(text = "Hey a Custom SnackBar")
            }

        }
    }

}