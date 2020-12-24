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
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Named


class RecipeListViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String
) : ViewModel() {
    val hRecipeListMS: MutableState<List<Recipe>> = mutableStateOf(listOf())

    init {

        Timber.d("Repository %s", hRecipeRepository)
        Timber.d("Token %s", hToken)

        viewModelScope.launch {
            val hSearch = hRecipeRepository.hSearch(
                token = hToken,
                page = 1,
                query = "chicken"
            )
            hRecipeListMS.value = hSearch
        }
    }

}