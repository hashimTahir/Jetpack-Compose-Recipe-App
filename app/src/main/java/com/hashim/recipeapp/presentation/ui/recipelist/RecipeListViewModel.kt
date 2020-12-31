/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Named


class RecipeListViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String
) : ViewModel() {
    val hRecipeListMS: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val hQuery = mutableStateOf("")
    val hSelectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    val hIsLoading = mutableStateOf(false)

    var hCategoryScroolPostion: Float = 0F


    init {
        hNewSearch()
    }

    fun hNewSearch() {
        viewModelScope.launch {
            hIsLoading.value = true

            hResetSearchState()

            delay(2000)
            val hSearchResult = hRecipeRepository.hSearch(
                token = hToken,
                page = 1,
                query = hQuery.value
            )
            hRecipeListMS.value = hSearchResult
            hIsLoading.value = false
        }
    }

    fun hOnQueryChanged(query: String) {
        hQuery.value = query

    }

    fun hOnSelectedCategoryChanged(category: String) {
        val hNewCategory = hGetFoodCategory(category)
        hSelectedCategory.value = hNewCategory
        hOnQueryChanged(category)
    }

    fun hOnSetCategoryScroolPosition(position: Float) {
        hCategoryScroolPostion = position
    }

    fun hClearSelectedCategory() {
        hSelectedCategory.value = null
    }

    fun hResetSearchState() {
        hRecipeListMS.value = listOf()
        if (hSelectedCategory.value?.value != hQuery.value) {
            hClearSelectedCategory()
        }

    }
}