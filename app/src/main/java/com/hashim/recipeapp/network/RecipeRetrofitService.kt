/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.network

import com.hashim.recipeapp.network.model.RecipeDto
import com.hashim.recipeapp.network.responses.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeRetrofitService {
    @GET("search")
    suspend fun hSearch(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse

    @GET("get")
    suspend fun hGet(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ): RecipeDto
}