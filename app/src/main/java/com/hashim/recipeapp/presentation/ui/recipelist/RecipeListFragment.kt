/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hashim.recipeapp.R
import com.hashim.recipeapp.presentation.ui.components.FoodCategoryChip
import com.hashim.recipeapp.presentation.ui.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    @Inject
    lateinit var hGson: Gson
    val hRecipeListViewModel: RecipeListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val value = hRecipeListViewModel.hRecipeListMS.value

                val query = hRecipeListViewModel.hQuery.value
                val selectedCategory = hRecipeListViewModel.hSelectedCategory.value

                Column {
                    Surface(
                        elevation = 8.dp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    ) {

                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextField(
                                    value = query,
                                    onValueChange = { newValue ->
                                        hRecipeListViewModel.hOnQueryChanged(newValue)
                                    },
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    label = {
                                        Text(text = "Search")
                                    },
                                    leadingIcon = {
                                        Icon(Icons.Filled.Search)
                                    },
                                    onImeActionPerformed = { action, softKeyboardController ->
                                        if (action == ImeAction.Search) {
                                            hRecipeListViewModel.hNewSearch()
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

                            }


                            ScrollableRow(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(start = 8.dp, bottom = 8.dp)
                            ) {
                                for (category in hGetAllFoodCategories()) {
                                    FoodCategoryChip(
                                        category = category.value,
                                        isSelected = selectedCategory == category,
                                        onSelectedCategoryChanged = {
                                            hRecipeListViewModel.hOnSelectedCategoryChanged(category = it)
                                        },
                                        onExecuteSearch = {
                                            hRecipeListViewModel.hNewSearch()
                                        }
                                    )
                                }

                            }
                        }
                    }
                    LazyColumn {
                        itemsIndexed(
                            items = value
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onclick = {})
                        }
                    }
                }
            }
        }
    }
}