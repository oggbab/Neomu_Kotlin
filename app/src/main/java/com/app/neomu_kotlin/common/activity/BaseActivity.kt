package com.app.neomu_kotlin.common.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.common.constanse.CommonConstance
import com.google.firebase.auth.FirebaseAuth
import com.not.app.club.NearFragment
import com.not.app.club.NewFragment
import com.not.app.club.PopularFragment
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClick(view: View?) {
    }
}