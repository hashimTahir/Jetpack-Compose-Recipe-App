/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hashim.recipeapp.Constants
import com.hashim.recipeapp.presentation.BaseApplication
import com.hashim.recipeapp.presentation.theme.AppTheme
import com.hashim.recipeapp.presentation.ui.components.CircularProgressBar
import com.hashim.recipeapp.presentation.ui.components.HsnackBar
import com.hashim.recipeapp.presentation.ui.components.LoadingRecipeShimmer
import com.hashim.recipeapp.presentation.ui.components.RecipeCompose
import com.hashim.recipeapp.presentation.ui.components.utils.SnackbarController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ReciepeFragment : Fragment() {

    private val hRecipeViewModel: RecipeViewModel by viewModels()

    @Inject
    lateinit var hApplication: BaseApplication

    private val hSnackBarController = SnackbarController(lifecycleScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(Constants.H_RECIPE_ID)?.let {
            hRecipeViewModel.hOnTriggerEvent(RecipeEvent.hGetRecipeEvent(it))
        }
    }

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val hLoading = hRecipeViewModel.hIsLodaing.value
                val hRecipe = hRecipeViewModel.hRecipeMS.value
                val hScafoldState = rememberScaffoldState()

                AppTheme(
                    darkTheme = hApplication.hIsdark.value,
                    displayProgressBar = hLoading,
                    scafoldState = hScafoldState
                ) {
                    Scaffold(
                        scaffoldState = hScafoldState,
                        snackbarHost = {
                            hScafoldState.snackbarHostState
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize())
                        {
                            if (hLoading && hRecipe == null) {
                                LoadingRecipeShimmer(cardHeigt = 260.dp)
                            } else {
                                hRecipe?.let {
                                    if (it.id == 1) {
                                        hSnackBarController.hShowSnackbar(
                                            scaffoldState = hScafoldState,
                                            message = "An error occured",
                                            actionLabel = "Ok"
                                        )
                                    } else {
                                        RecipeCompose(recipe = it)
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

}