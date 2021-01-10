/*
 * Copyright (c) 2021/  1/ 10.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@ExperimentalMaterialApi
@Composable
fun DecoupledSnackBar(
    snackbarHostState: SnackbarHostState
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val hSnackBar = createRef()
        SnackbarHost(
            modifier = Modifier.constrainAs(hSnackBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel
                                    ?: "Hide ",
                                style = TextStyle(color = Color.White)
                            )
                        }
                    }
                ) {
                    Text(
                        text = snackbarHostState.currentSnackbarData?.message
                            ?: "Unknown message "
                    )
                }
            }
        )
    }
}