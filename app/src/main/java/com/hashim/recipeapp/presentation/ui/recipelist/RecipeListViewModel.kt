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
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent.hNewSearchEvent
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent.hNextPageEvent
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Named

const val H_PAGE_SIZE = 30

class RecipeListViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String
) : ViewModel() {
    val hRecipeListMS: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val hQuery = mutableStateOf("")
    val hSelectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val hPage = mutableStateOf(1)
    private var hRecipeListScrollPosition = 0

    val hIsLoading = mutableStateOf(false)

    var hCategoryScroolPostion: Float = 0F


    init {
        hOnTriggerEvent(hNewSearchEvent)
    }

    fun hOnTriggerEvent(event: RecipeListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is hNewSearchEvent -> {
                        hNewSearch()
                    }
                    is hNextPageEvent -> {
                        hGetNextPage()
                    }
                }

            } catch (e: Exception) {
                Timber.d("Exception $e")
            }
        }
    }

    private suspend fun hNewSearch() {

        hIsLoading.value = true

        hResetSearchState()

        delay(3000)
        val hSearchResult = hRecipeRepository.hSearch(
            token = hToken,
            page = 1,
            query = hQuery.value
        )
        hRecipeListMS.value = hSearchResult
        hIsLoading.value = false

    }

    private suspend fun hGetNextPage() {

        if ((hRecipeListScrollPosition + 1) >= (hPage.value * H_PAGE_SIZE)) {
            hIsLoading.value = true
            hIncrementPage()
            Timber.d("Next Page triggered %s", hPage.value)

            delay(1000)
            if (hPage.value > 1) {
                val hResult = hRecipeRepository.hSearch(
                    token = hToken,
                    page = hPage.value,
                    query = hQuery.value
                )
                hAppendRecipeList(hResult)
            }
            hIsLoading.value = false
        }

    }

    fun hOnQueryChanged(query: String) {
        hQuery.value = query

    }

    fun hAppendRecipeList(recipeList: List<Recipe>) {
        val hCurrentList = ArrayList(hRecipeListMS.value)
        hCurrentList.addAll(recipeList)
        hRecipeListMS.value = hCurrentList

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
        hPage.value = 1
        OnChangeRecipeScrollPosition(0)
        if (hSelectedCategory.value?.value != hQuery.value) {
            hClearSelectedCategory()
        }

    }

    fun OnChangeRecipeScrollPosition(position: Int) {
        hRecipeListScrollPosition = position
    }

    private fun hIncrementPage() {
        hPage.value = hPage.value++
    }
}