/*
 * Copyright (c) 2020/  12/ 30.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hashim.recipeapp.presentation.ui.recipelist.FoodCategory

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    scrollPosition: Float,
    onChangeScrollPosition: (Float) -> Unit,
    onToggleTheme: () -> Unit
) {
    Surface(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = query,
                    onValueChange = { newValue ->
                        onQueryChanged(newValue)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    label = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Search)
                    },
                    onImeActionPerformed = { action, softKeyboardController ->
                        if (action == ImeAction.Search) {
                            onExecuteSearch()
                            softKeyboardController?.hideSoftwareKeyboard()

                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    backgroundColor = MaterialTheme.colors.surface,
                )
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val hMenu = createRef()
                    IconButton(
                        onClick = { onToggleTheme() },
                        modifier = Modifier.constrainAs(hMenu) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        })
                    {
                        Icon(Icons.Filled.MoreVert)
                    }
                }
            }

            val hScrollState = rememberScrollState()

            ScrollableRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                scrollState = hScrollState
            ) {
                hScrollState.scrollTo(scrollPosition)
                for (category in categories) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {
                            onChangeScrollPosition(hScrollState.value)
                            onSelectedCategoryChanged(it)
                        },
                        onExecuteSearch = {
                            onExecuteSearch()
                        }
                    )
                }
            }
        }
    }
}