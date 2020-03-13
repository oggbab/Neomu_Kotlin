package com.app.neomu_kotlin.common.util

import androidx.fragment.app.Fragment

interface FragmentUtilImpl {
    fun addFragmnet(fragment: Fragment, containerId: Int)
    fun replaceFragment(fragment: Fragment, containerId: Int)
    fun finishFragment()
}