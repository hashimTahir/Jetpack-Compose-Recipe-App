/*
 * Copyright (c) 2021/  1/ 11.  Created by Hashim Tahir
 */

package com.hashim.recipeapp.presentation.ui.components.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackbarController(
    private val scope: CoroutineScope
) {

    private var hSnackBarJob: Job? = null

    fun hGetScope() = scope

    init {
        hCancelActiveJobs()
    }

    @ExperimentalMaterialApi
    fun hShowSnackbar(
        scaffoldState: ScaffoldState,
        message: String,
        actionLabel: String
    ) {

        if (hSnackBarJob == null) {
            hSnackBarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
                hCancelActiveJobs()
            }
        } else {
            hCancelActiveJobs()
            hSnackBarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel
                )
                hCancelActiveJobs()
            }
        }
    }

    private fun hCancelActiveJobs() {
        hSnackBarJob?.let { job ->
            job.cancel()
            hSnackBarJob = Job()
        }
    }
}