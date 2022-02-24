package com.example.qrapp.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrapp.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}