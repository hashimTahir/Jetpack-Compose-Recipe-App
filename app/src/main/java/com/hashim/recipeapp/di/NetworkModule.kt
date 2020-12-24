/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.di

import com.google.gson.Gson
import com.hashim.recipeapp.network.RecipeRetrofitService
import com.hashim.recipeapp.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun hProvidesRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }


    @Singleton
    @Provides
    fun hProvidesLogginInterceptor() =
        HttpLoggingInterceptor()


    @Singleton
    @Provides
    fun hProvidesHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            loggingInterceptor.setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()
    }


    @Singleton
    @Provides
    fun hProvidesRecipeRetrofitService(
        httpClient: OkHttpClient,
        gson: Gson
    ): RecipeRetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    gson
                )
            )
            .client(httpClient)
            .build()
            .create(RecipeRetrofitService::class.java)

    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun hProvidesAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}