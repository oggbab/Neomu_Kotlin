package com.app.neomu_kotlin.app.club.activity

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.ClubFragment
import com.app.neomu_kotlin.app.club.fragment.MainFragment
import neomu.kotlin.app.main.ToolbarActivity

class MainActivity : ToolbarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}