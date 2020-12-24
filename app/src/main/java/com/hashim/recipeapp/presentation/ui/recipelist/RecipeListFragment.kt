/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.hashim.recipeapp.R
import com.hashim.recipeapp.presentation.ui.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    @Inject
    lateinit var hGson: Gson
    val hRecipeListViewModel: RecipeListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val value = hRecipeListViewModel.hRecipeListMS.value

                val query = hRecipeListViewModel.hQuery.value

                Column {

                    TextField(
                        value = query,
                        onValueChange = { newValue ->
                            hRecipeListViewModel.hOnQueryChanged(newValue)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    LazyColumn {
                        itemsIndexed(
                            items = value
                        ) { index, recipe ->
                            RecipeCard(recipe = recipe, onclick = {})
                        }
                    }
                }


            }
        }
    }
}