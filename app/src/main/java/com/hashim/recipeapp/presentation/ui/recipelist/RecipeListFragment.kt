/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hashim.recipeapp.presentation.BaseApplication
import com.hashim.recipeapp.presentation.theme.AppTheme
import com.hashim.recipeapp.presentation.ui.components.CircularProgressBar
import com.hashim.recipeapp.presentation.ui.components.LoadingRecipeListShimmer
import com.hashim.recipeapp.presentation.ui.components.RecipeCard
import com.hashim.recipeapp.presentation.ui.components.SearchAppBar
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
                AppTheme(
                    darkTheme = hApplication.hIsdark.value
                ) {
                    val value = hRecipeListViewModel.hRecipeListMS.value
                    val query = hRecipeListViewModel.hQuery.value
                    val selectedCategory = hRecipeListViewModel.hSelectedCategory.value
                    val categoryScrollPosition = hRecipeListViewModel.hCategoryScroolPostion
                    val hIsLoading = hRecipeListViewModel.hIsLoading.value

                    Column {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = hRecipeListViewModel::hOnQueryChanged,
                            onExecuteSearch = hRecipeListViewModel::hNewSearch,
                            categories = hGetAllFoodCategories(),
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = hRecipeListViewModel::hOnSelectedCategoryChanged,
                            scrollPosition = categoryScrollPosition,
                            onChangeScrollPosition = hRecipeListViewModel::hOnSetCategoryScroolPosition,
                            onToggleTheme = {
                                hApplication.hToggleTheme()
                            }
                        )

                        /*Box is like a frame layout
                        * children are stacked on top of one and other with priority from
                        * top to bottom*/
                        Box(modifier = Modifier.fillMaxSize()) {
                            if (hIsLoading) {
                                LoadingRecipeListShimmer(cardHeigt = 250.dp)

                            } else {
                                LazyColumn {
                                    itemsIndexed(
                                        items = value
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onclick = {})
                                    }
                                }
                            }

                            CircularProgressBar(isDisplayed = hIsLoading)
                        }
                    }
                }

            }
        }
    }
}

