package com.v15h4l.wardrobe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.v15h4l.wardrobe.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}