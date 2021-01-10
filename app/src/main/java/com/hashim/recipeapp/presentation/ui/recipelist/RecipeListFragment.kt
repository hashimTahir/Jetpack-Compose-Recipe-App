/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hashim.recipeapp.presentation.BaseApplication
import com.hashim.recipeapp.presentation.ui.components.SimpleSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var hGson: Gson

    @Inject
    lateinit var hApplication: BaseApplication
    val hRecipeListViewModel: RecipeListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val hIsShowing = remember { mutableStateOf(false) }
                Column {
                    Button(
                        onClick = {
                            hIsShowing.value = true
                        }
                    ) {
                        Text(text = "Show SnackBar")
                    }
                    SimpleSnackBar(
                        isShowing = hIsShowing.value,
                        onHideSnackBar = {
                            hIsShowing.value = false
                        }
                    )
                }

//                AppTheme(
//                    darkTheme = hApplication.hIsdark.value
//                ) {
//                    val value = hRecipeListViewModel.hRecipeListMS.value
//                    val query = hRecipeListViewModel.hQuery.value
//                    val selectedCategory = hRecipeListViewModel.hSelectedCategory.value
//                    val categoryScrollPosition = hRecipeListViewModel.hCategoryScroolPostion
//                    val hIsLoading = hRecipeListViewModel.hIsLoading.value
//
//                    /*Scaffold is used to add top, bottom, and drawers like componets
//                    * with compose.
//                    * content goes in content function*/
//
//                    Scaffold(
//                        topBar = {
//                            SearchAppBar(
//                                query = query,
//                                onQueryChanged = hRecipeListViewModel::hOnQueryChanged,
//                                onExecuteSearch = hRecipeListViewModel::hNewSearch,
//                                categories = hGetAllFoodCategories(),
//                                selectedCategory = selectedCategory,
//                                onSelectedCategoryChanged = hRecipeListViewModel::hOnSelectedCategoryChanged,
//                                scrollPosition = categoryScrollPosition,
//                                onChangeScrollPosition = hRecipeListViewModel::hOnSetCategoryScroolPosition,
//                                onToggleTheme = {
//                                    hApplication.hToggleTheme()
//                                }
//                            )
//                        },
//                    ) {
//                        /*Box is like a frame layout
//                                              * children are stacked on top of one and other with priority from
//                                              * top to bottom*/
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(
//                                    color = MaterialTheme.colors.surface
//                                )
//                        ) {
//                            if (hIsLoading) {
//                                LoadingRecipeListShimmer(cardHeigt = 250.dp)
//
//                            } else {
//                                LazyColumn {
//                                    itemsIndexed(
//                                        items = value
//                                    ) { index, recipe ->
//                                        RecipeCard(recipe = recipe, onclick = {})
//                                    }
//                                }
//                            }
//
//                            CircularProgressBar(isDisplayed = hIsLoading)
//                        }
//                    }
//                }

            }
        }
    }
}

