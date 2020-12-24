/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.domain.util

interface DomainMapper<T, DomainModel> {

    fun hMapToDomainModel(model: T): DomainModel

    fun hMapFromDomailModel(domainModel: DomainModel): T
}