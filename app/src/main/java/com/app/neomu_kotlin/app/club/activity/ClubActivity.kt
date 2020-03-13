package com.app.neomu_kotlin.app.club.activity

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.ClubFragment
import com.app.neomu_kotlin.common.activity.BaseActivity

class ClubActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club)
        replaceFragment(ClubFragment(), R.id.fr_club)
    }
}