/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.network.model

import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    override fun hMapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients ?: listOf(),
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated
        )
    }

    override fun hMapFromDomailModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }


    fun hToDomainList(list: List<RecipeDto>): List<Recipe> {
        return list.map {
            hMapToDomainModel(it)
        }
    }

    fun hFromDomainList(list: List<Recipe>): List<RecipeDto> {
        return list.map {
            hMapFromDomailModel(it)
        }
    }
}