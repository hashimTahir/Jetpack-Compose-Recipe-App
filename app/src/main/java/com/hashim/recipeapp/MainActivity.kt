/*
 * Copyright (c) 2020/  12/ 22.  Created by Hashim Tahir
 */

package com.hashim.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableColumn(
                modifier = Modifier
                    .background(
                        color = Color(0xFFF2F2F2)
                    )
                    .fillMaxHeight()
            ) {
                Image(
                    modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop,
                    bitmap = imageFromResource(
                        res = resources,
                        resId = R.drawable.happy_meal
                    )
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Happy Meal",
                            style = TextStyle(
                                fontSize = TextUnit.Sp(26),

                                )
                        )

                        Text(
                            text = "$5.99",
                            style = TextStyle(
                                color = Color(0xFF85bb64),
                                fontSize = TextUnit.Sp(10)
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                    }


                    Spacer(
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                    Text(
                        text = "800 Calories",
                        style = TextStyle(
                            fontSize = TextUnit.Sp(17)
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )

                    Button(
                        onClick = { },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    ) {
                        Text(
                            text = "Order now",
                            style = TextStyle(
                                fontSize = TextUnit.Sp(17)
                            )
                        )
                    }


                }
            }

        }
    }
}