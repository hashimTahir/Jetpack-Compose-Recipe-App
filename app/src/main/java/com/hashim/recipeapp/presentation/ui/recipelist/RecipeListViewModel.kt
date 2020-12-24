/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import timber.log.Timber
import javax.inject.Named


class RecipeListViewModel @ViewModelInject constructor(
    private val hRecipeRepository: RecipeRepositoryImpl,
    private @Named("auth_token") val hToken: String
) : ViewModel() {
    init {

        Timber.d("Repository %s", hRecipeRepository)
        Timber.d("Token %s", hToken)

    }

}