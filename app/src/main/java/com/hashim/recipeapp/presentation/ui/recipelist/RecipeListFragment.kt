/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hashim.recipeapp.presentation.ui.components.LoadingRecipeListShimmer
import com.hashim.recipeapp.presentation.ui.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

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
                    )
                    LoadingRecipeListShimmer(cardHeigt = 250.dp)
                }
            }
        }
    }
}

