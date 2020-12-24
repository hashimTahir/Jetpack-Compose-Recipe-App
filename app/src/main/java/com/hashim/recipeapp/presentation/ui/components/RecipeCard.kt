/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.R
import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.utils.hLoadPicture

@Composable
fun RecipeCard(
    recipe: Recipe,
    onclick: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 8.dp,
                end = 8.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onclick),
        elevation = 8.dp

    ) {
        Column() {
            recipe.featuredImage?.let { url ->
                val hImage = hLoadPicture(
                    url = url,
                    defaultImage = R.drawable.empty_plate
                ).value

                hImage?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .preferredHeight(225.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            top = 12.dp,
                            bottom = 12.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }


}