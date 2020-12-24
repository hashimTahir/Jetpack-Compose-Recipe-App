/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.repository

import com.hashim.recipeapp.domain.model.Recipe

interface RecipeRepository {
    suspend fun hSearch(token: String, page: Int, query: String): List<Recipe>

    suspend fun hGet(token: String, id: Int): Recipe

}