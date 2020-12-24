/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.di

import com.hashim.recipeapp.network.RecipeRetrofitService
import com.hashim.recipeapp.network.model.RecipeDtoMapper
import com.hashim.recipeapp.repository.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun hProvidesRecipeRepo(
        retrofitService: RecipeRetrofitService,
        mapper: RecipeDtoMapper
    ): RecipeRepositoryImpl {
        return RecipeRepositoryImpl(
            retrofitService,
            mapper
        )
    }


}