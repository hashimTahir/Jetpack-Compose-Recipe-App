/*
 * Copyright (c) 2020/  12/ 22.  Created by Hashim Tahir
 */

package com.hashim.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.platform.setContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Image(
                bitmap = imageFromResource(
                    res = resources, resId = R.drawable.happy_meal
                )
            )
        }
    }
}