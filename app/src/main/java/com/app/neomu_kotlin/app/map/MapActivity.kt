package com.app.neomu_kotlin.app.map

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.common.activity.BaseActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(MapFragment(), R.id.fr_map)
    }
}