package com.app.neomu_kotlin.app.club.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.PostDetailFragment
import com.app.neomu_kotlin.app.intro.model.CommentModel
import com.app.neomu_kotlin.app.intro.model.PostModel
import com.app.neomu_kotlin.common.activity.BaseActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.not.app.club.util.CommentAdapter
import com.not.app.models.User
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.include_post_author.*
import kotlinx.android.synthetic.main.include_post_date.*
import kotlinx.android.synthetic.main.include_post_infor.*
import kotlinx.android.synthetic.main.include_post_text.*
import neomu.kotlin.common.util.FirebaseUtil
import org.jetbrains.anko.intentFor

class PostDetailActivity : BaseActivity() {

    private lateinit var mPostListener: ValueEventListener
    private lateinit var mPostReference: DatabaseReference
    private var isListener: Boolean = false
    private lateinit var mAdapter: CommentAdapter
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        replaceFragment(PostDetailFragment(), R.id.fr_post_detail)
    }
}