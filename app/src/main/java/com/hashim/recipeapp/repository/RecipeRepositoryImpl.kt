/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.repository

import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.network.RecipeRetrofitService
import com.hashim.recipeapp.network.model.RecipeDtoMapper

class RecipeRepositoryImpl(
    private val hRecipeRetrofitService: RecipeRetrofitService,
    private val hMapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun hSearch(token: String, page: Int, query: String): List<Recipe> {
        return hMapper.hToDomainList(
            hRecipeRetrofitService.hSearch(
                token = token,
                page = page,
                query = query
            ).recipes
        )
    }

    override suspend fun hGet(token: String, id: Int): Recipe {
        return hMapper.hMapToDomainModel(
            hRecipeRetrofitService.hGet(
                token = token,
                id = id
            )
        )
    }
}