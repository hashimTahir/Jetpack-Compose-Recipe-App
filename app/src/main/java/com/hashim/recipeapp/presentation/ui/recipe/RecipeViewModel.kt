/*
 * Copyright (c) 2021/  1/ 19.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Named

const val H_STATE_KEY_RECIPE = "hRecipeStateKey"

class RecipeViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val hRecipeMS: MutableState<Recipe?> = mutableStateOf(null)
    val hIsLodaing = mutableStateOf(false)

    init {
        savedStateHandle.get<Int>(H_STATE_KEY_RECIPE)?.let {
            hOnTriggerEvent(RecipeEvent.hGetRecipeEvent(it))
        }

    }

     fun hOnTriggerEvent(recipeEvent: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (recipeEvent) {
                    is RecipeEvent.hGetRecipeEvent -> {
                        if (hRecipeMS.value == null) {
                            hGetRecipe(recipeEvent.hId)
                        }
                    }

                }
            } catch (e: Exception) {

            }

        }
    }

    private suspend fun hGetRecipe(hId: Int) {
        hIsLodaing.value = true
        delay(1000)
        val hRecipe = hRecipeRepository.hGet(
            token = hToken,
            id = hId
        )
        hRecipeMS.value = hRecipe
        savedStateHandle.set(
            H_STATE_KEY_RECIPE, hRecipe.id
        )
        hIsLodaing.value = false
    }
}
