/*
 * Copyright (c) 2021/  1/ 8.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hashim.recipeapp.presentation.ui.components.CircularProgressBar
import com.hashim.recipeapp.presentation.ui.components.HsnackBar


private val LightThemeColors = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

private val DarkThemeColors = darkColors(
    primary = Blue700,
    primaryVariant = Color.White,
    onPrimary = Color.White,
    secondary = Black1,
    onSecondary = Color.White,
    error = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White,
)

@ExperimentalMaterialApi
@Composable
fun AppTheme(
    darkTheme: Boolean,
    displayProgressBar: Boolean,
    scafoldState: ScaffoldState,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (!darkTheme) Grey1 else Color.Black)
        ) {
            content()
            CircularProgressBar(isDisplayed = displayProgressBar, 0.3F)

            HsnackBar(
                snackbarHostState = scafoldState.snackbarHostState,
                onDismiss = {
                    scafoldState.snackbarHostState
                        .currentSnackbarData?.dismiss()
                },
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            )
        }


    }
}
