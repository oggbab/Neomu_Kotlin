package com.app.neomu_kotlin.common.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.neomu_kotlin.common.util.FragmentUtilImpl

open class BaseActivity : AppCompatActivity(), View.OnClickListener, FragmentUtilImpl {

    lateinit var mFragmentManager : FragmentManager
    lateinit var mFragmentTransition : FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    init {
        mFragmentManager = supportFragmentManager
        mFragmentTransition = mFragmentManager.beginTransaction()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClick(view: View?) {

    }

    override fun addFragmnet(fragment: Fragment, containerId: Int) {
        mFragmentTransition.add(containerId, fragment)
        mFragmentTransition.addToBackStack(null)
        mFragmentTransition.commitAllowingStateLoss()

    }

    override fun replaceFragment(fragment: Fragment, containerId: Int) {
        mFragmentTransition.replace(containerId, fragment)
        mFragmentTransition.commitAllowingStateLoss()
    }

    override fun finishFragment() {
        this.finish()
    }
}