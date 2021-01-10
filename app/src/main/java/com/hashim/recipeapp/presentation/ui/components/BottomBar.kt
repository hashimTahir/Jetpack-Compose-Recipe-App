/*
 * Copyright (c) 2021/  1/ 10.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatFlat
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Headset
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBar() {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.BrokenImage) },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AirlineSeatFlat) },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Headset) },
            selected = false,
            onClick = { /*TODO*/ }
        )

    }
}