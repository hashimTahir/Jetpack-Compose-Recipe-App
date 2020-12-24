/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.domain.util

interface EntityMapper<Entity, DomainModel> {

    fun hMapFromEntity(entity: Entity): DomainModel

    fun hMapToEntity(domainModel: DomainModel): Entity
}