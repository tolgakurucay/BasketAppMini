package com.tolgakurucay.shoppingappforinterview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tolgakurucay.shoppingappforinterview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.from_right,R.anim.to_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}