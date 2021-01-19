/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent.*
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Named

const val H_PAGE_SIZE = 30


const val H_STATE_KEY_PAGE = "PAGE.KEY"
const val H_STATE_KEY_QUERY = "QUERY.KEY"
const val H_STATE_KEY_LIST_POSITION = "LIST.POSITION"
const val H_STATE_KEY_SELECTED_CATEGORY = "STATE.QUERY"

class RecipeListViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val hRecipeListMS: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val hQuery = mutableStateOf("")
    val hSelectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val hPage = mutableStateOf(1)
    private var hRecipeListScrollPosition = 0

    val hIsLoading = mutableStateOf(false)

    var hCategoryScroolPostion: Float = 0F


    init {
        savedStateHandle.get<Int>(H_STATE_KEY_PAGE)?.let { page ->
            hSetPage(page = page)
        }
        savedStateHandle.get<String>(H_STATE_KEY_QUERY)?.let { query ->
            hSetQuery(query = query)
        }
        savedStateHandle.get<Int>(H_STATE_KEY_LIST_POSITION)?.let { postition ->
            hSetListScrollPosition(position = postition)
        }
        savedStateHandle.get<FoodCategory>(H_STATE_KEY_SELECTED_CATEGORY)?.let { category ->
            hSetSelectedCategory(category = category)
        }

        if (hRecipeListScrollPosition != 0) {
            hOnTriggerEvent(hRestoreStateEvent)
        } else {
            hOnTriggerEvent(hNewSearchEvent)

        }

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
                    is hRestoreStateEvent -> {
                        hRestoreState()
                    }
                }

            } catch (e: Exception) {
                Timber.d("Exception $e")
            }
        }
    }

    private suspend fun hRestoreState() {
        hIsLoading.value = true
        val hResults: MutableList<Recipe> = mutableListOf()
        for (p in 1..hPage.value) {
            val result = hRecipeRepository.hSearch(
                token = hToken,
                page = p,
                query = hQuery.value
            )
            hResults.addAll(result)
            if (p == hPage.value) {
                hRecipeListMS.value = hResults
                hIsLoading.value = false
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
        hSetQuery(query = query)

    }

    fun hAppendRecipeList(recipeList: List<Recipe>) {
        val hCurrentList = ArrayList(hRecipeListMS.value)
        hCurrentList.addAll(recipeList)
        hRecipeListMS.value = hCurrentList

    }

    fun hOnSelectedCategoryChanged(category: String) {
        val hNewCategory = hGetFoodCategory(category)
        hSetSelectedCategory(hNewCategory)
        hOnQueryChanged(category)
    }

    fun hOnSetCategoryScroolPosition(position: Float) {
        hCategoryScroolPostion = position
    }

    fun hClearSelectedCategory() {
        hSetSelectedCategory(null)
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
        hSetListScrollPosition(position = position)
    }

    private fun hIncrementPage() {
        hSetPage(hPage.value + 1)
    }

    private fun hSetListScrollPosition(position: Int) {
        hRecipeListScrollPosition = position
        savedStateHandle.set(H_STATE_KEY_LIST_POSITION, position)
    }

    private fun hSetPage(page: Int) {
        hPage.value = page
        savedStateHandle.set(H_STATE_KEY_PAGE, page)
    }

    private fun hSetSelectedCategory(category: FoodCategory?) {
        hSelectedCategory.value = category
        savedStateHandle.set(H_STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun hSetQuery(query: String) {
        hQuery.value = query
        savedStateHandle.set(H_STATE_KEY_QUERY, query)
    }
}