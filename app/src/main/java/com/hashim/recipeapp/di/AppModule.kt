/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.di

import android.content.Context
import com.hashim.recipeapp.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun hProvidesApplication(
        @ApplicationContext app: Context
    ): BaseApplication {
        return app as BaseApplication
    }

//    @Singleton
//    @Provides

}