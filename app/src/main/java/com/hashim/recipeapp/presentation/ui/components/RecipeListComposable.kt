/*
 * Copyright (c) 2021/  1/ 18.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hashim.recipeapp.Constants
import com.hashim.recipeapp.R
import com.hashim.recipeapp.domain.model.Recipe
import com.hashim.recipeapp.presentation.ui.components.utils.SnackbarController
import com.hashim.recipeapp.presentation.ui.recipelist.H_PAGE_SIZE
import com.hashim.recipeapp.presentation.ui.recipelist.RecipeListEvent
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun RecipeListComposable(
    isLoading: Boolean,
    recipeList: List<Recipe>,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    page: Int,
    oNTriggerEvent: (recipeListEvent: RecipeListEvent) -> Unit,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController,
    navController: NavController
) {
    /*Box is like a frame layout
                                           * children are stacked on top of one and other with priority from
                                           * top to bottom*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.surface
            )
    ) {
        if (isLoading && recipeList.isEmpty()) {
            LoadingRecipeListShimmer(cardHeigt = 250.dp)

        } else {
            LazyColumn {
                itemsIndexed(
                    items = recipeList
                ) { index, recipe ->
                    onChangeRecipeScrollPosition(index)
                    if (index + 1 >= (page * H_PAGE_SIZE) && !isLoading) {
                        oNTriggerEvent(RecipeListEvent.hNextPageEvent)

                    }
                    RecipeCard(
                        recipe = recipe,
                        onclick = {
                            if (recipe.id != null) {
                                val hBundle = Bundle()
                                hBundle.putInt(Constants.H_RECIPE_ID, recipe.id)
                                navController.navigate(
                                    R.id.action_hRecipeListFragment_to_hReciepeFragment,
                                    hBundle
                                )

                            } else {
                                snackbarController.hGetScope().launch {
                                    snackbarController.hShowSnackbar(
                                        scaffoldState = scaffoldState,
                                        message = "Recipe Error",
                                        actionLabel = "Ok"
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }

        CircularProgressBar(isDisplayed = isLoading,0.3F)

        HsnackBar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onDismiss = {
                scaffoldState.snackbarHostState
                    .currentSnackbarData?.dismiss()
            },
            modifier = Modifier.align(
                Alignment.BottomCenter
            )
        )
    }

}