/*
 * Copyright (c) 2020/  12/ 22.  Created by Hashim Tahir
 */

package com.hashim.recipeapp

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
import androidx.navigation.findNavController

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
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