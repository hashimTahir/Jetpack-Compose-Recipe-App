/*
 * Copyright (c) 2020/  12/ 22.  Created by Hashim Tahir
 */

package com.hashim.recipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    private lateinit var hNavHostFragment: NavHostFragment
    private lateinit var hNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}