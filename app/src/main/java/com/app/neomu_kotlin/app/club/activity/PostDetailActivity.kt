package com.app.neomu_kotlin.app.club.activity

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.PostDetailFragment
import com.app.neomu_kotlin.common.activity.BaseActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class PostDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        replaceFragment(PostDetailFragment(), R.id.fr_post_detail)
    }
}