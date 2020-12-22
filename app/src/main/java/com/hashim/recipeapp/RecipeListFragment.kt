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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val hView = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        hView.findViewById<ComposeView>(R.id.hComposeView)
            .setContent {
                Column(
                    modifier = Modifier
                        .border(border = BorderStroke(1.dp, Color.Black))
                        .padding(16.dp)

                ) {

                    Text("This is compose inside fragment layout")
                    Spacer(modifier = Modifier.padding(10.dp))
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text("New Text")
                    Spacer(modifier = Modifier.padding(10.dp))


                    val hCustomView = HorizontalDottedProgress(AmbientContext.current)
                    AndroidView(viewBlock = { hCustomView })
                }
            }
        return hView
    }

}