/*
 * Copyright (c) 2020/  12/ 24.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.hashim.recipeapp.presentation.BaseApplication
import com.hashim.recipeapp.presentation.theme.AppTheme
import com.hashim.recipeapp.presentation.ui.components.RecipeListComposable
import com.hashim.recipeapp.presentation.ui.components.SearchAppBar
import com.hashim.recipeapp.presentation.ui.components.utils.SnackbarController
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent.hNewSearchEvent
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent.hNextPageEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var hGson: Gson

    @Inject
    lateinit var hApplication: BaseApplication
    val hRecipeListViewModel: RecipeListViewModel by viewModels()

    private val hSnackBarController = SnackbarController(lifecycleScope)


    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {


                AppTheme(
                    darkTheme = hApplication.hIsdark.value
                ) {
                    val hRecipeList = hRecipeListViewModel.hRecipeListMS.value
                    val query = hRecipeListViewModel.hQuery.value
                    val selectedCategory = hRecipeListViewModel.hSelectedCategory.value
                    val categoryScrollPosition = hRecipeListViewModel.hCategoryScroolPostion
                    val hIsLoading = hRecipeListViewModel.hIsLoading.value

                    val hPage = hRecipeListViewModel.hPage.value

                    /*Scaffold is used to add top, bottom, and drawers like componets
                    * with compose.
                    * content goes in content function*/

                    val hScaffoldState = rememberScaffoldState()
                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = hRecipeListViewModel::hOnQueryChanged,
                                onExecuteSearch = {
                                    if (hRecipeListViewModel.hSelectedCategory
                                            .value?.value == "Milk"
                                    ) {
                                        hSnackBarController.hGetScope().launch {
                                            hSnackBarController.hShowSnackbar(
                                                scaffoldState = hScaffoldState,
                                                message = "Invalid Category : Milk",
                                                actionLabel = "Hide",
                                            )
                                        }
                                    } else {
                                        hRecipeListViewModel.hOnTriggerEvent(hNewSearchEvent)
                                    }
                                },
                                categories = hGetAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = hRecipeListViewModel::hOnSelectedCategoryChanged,
                                scrollPosition = categoryScrollPosition,
                                onChangeScrollPosition = hRecipeListViewModel::hOnSetCategoryScroolPosition,
                                onToggleTheme = {
                                    hApplication.hToggleTheme()
                                }
                            )
                        },
                        scaffoldState = hScaffoldState,
                        snackbarHost = {
                            hScaffoldState.snackbarHostState
                        }
                    ) {
                        RecipeListComposable(
                            isLoading = hIsLoading,
                            recipeList = hRecipeList,
                            onChangeRecipeScrollPosition = {
                                hRecipeListViewModel::OnChangeRecipeScrollPosition
                            },
                            page = hPage,
                            oNTriggerEvent = {
                                hRecipeListViewModel.hOnTriggerEvent(hNextPageEvent)
                            },
                            scaffoldState = hScaffoldState,
                            snackbarController = hSnackBarController,
                            navController = findNavController()
                        )


                    }
                }

            }
        }
    }
}

