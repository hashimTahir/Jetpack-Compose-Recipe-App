/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.hashim.recipeapp.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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
                Timber.d("Data List %s", hGson.toJson(value))
                Column(
                    modifier = Modifier
                        .border(border = BorderStroke(1.dp, Color.Black))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Recipe List fragment",
                        style = TextStyle(
                            fontSize = TextUnit.Companion.Sp(21)
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Button(onClick = {
                        findNavController().navigate(R.id.action_hRecipeListFragment_to_hReciepeFragment)
                    }) {
                        Text(text = "To Recipe Fragment")
                    }
                }
            }
        }
    }
}