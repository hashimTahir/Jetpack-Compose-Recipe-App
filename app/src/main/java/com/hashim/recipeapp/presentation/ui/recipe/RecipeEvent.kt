/*
 * Copyright (c) 2021/  1/ 18.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipe

sealed class RecipeEvent {
    data class hGetRecipeEvent(
        val hId: Int
    ) : RecipeEvent()
}