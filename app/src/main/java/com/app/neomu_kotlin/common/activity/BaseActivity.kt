package com.app.neomu_kotlin.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.app.neomu_kotlin.common.constanse.CommonConstance
import com.google.firebase.auth.FirebaseAuth
import com.not.app.club.NearFragment
import com.not.app.club.NewFragment
import com.not.app.club.PopularFragment

open class BaseActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun setCustomToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(tool)
    }

    fun addFragment() {

    }

    fun getFragmentPagerAdapter(): FragmentPagerAdapter {
        val fragmentPagerAdapter = object: FragmentPagerAdapter(supportFragmentManager) {
            val fragmentArray = arrayListOf<Fragment>(
                    PopularFragment(), NewFragment(), NearFragment()
            )

            val fragmentName = arrayListOf<String>(
                    CommonConstance.FRAGMENT_TAB_NAME_POPULAR,
                    CommonConstance.FRAGMENT_TAB_NAME_NEW,
                    CommonConstance.FRAGMENT_TAB_NAME_NEAR)

            override fun getItem(position: Int): Fragment {
                return fragmentArray[position]
            }

            override fun getCount(): Int {
                return fragmentArray.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragmentName[position]
            }

        }
        return fragmentPagerAdapter
    }

    companion object {

    }

}