/*
 * Copyright (c) 2021/  1/ 18.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

sealed class RecipeListEvent {
    object hNewSearchEvent : RecipeListEvent()
    object hNextPageEvent : RecipeListEvent()
}