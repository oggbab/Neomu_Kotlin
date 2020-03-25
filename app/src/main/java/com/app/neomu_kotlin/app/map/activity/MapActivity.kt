package com.app.neomu_kotlin.app.map.activity

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.map.fragment.MapFragment
import com.app.neomu_kotlin.common.activity.BaseActivity

class MapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(MapFragment(), R.id.fr_map)
    }
}