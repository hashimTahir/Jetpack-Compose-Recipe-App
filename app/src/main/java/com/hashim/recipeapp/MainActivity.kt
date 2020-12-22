/*
 * Copyright (c) 2020/  12/ 22.  Created by Hashim Tahir
 */

package com.hashim.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "This is item 1",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "This is item 2",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "This is item 1",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    )
                }
            }


        }
    }
}