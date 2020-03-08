package com.app.neomu_kotlin.app.club.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.intro.model.CommentModel
import com.app.neomu_kotlin.app.intro.model.PostModel
import com.app.neomu_kotlin.app.main.model.UserInfoModel
import com.google.firebase.database.*
import com.not.app.club.util.CommentAdapter
import com.not.app.models.Post
import com.not.app.models.User
import com.not.common.BaseActivity
import kotlinx.android.synthetic.main.activity_post_detail.*
import kotlinx.android.synthetic.main.include_post_author.*
import kotlinx.android.synthetic.main.include_post_date.*
import kotlinx.android.synthetic.main.include_post_infor.*
import kotlinx.android.synthetic.main.include_post_text.*
import neomu.kotlin.common.util.FirebaseUtil

class PostDetailActivity : BaseActivity() {

    private lateinit var mPostListener: ValueEventListener
    private lateinit var mPostReference: DatabaseReference
    private var isListener: Boolean = false
    private lateinit var mAdapter: CommentAdapter
    private lateinit var userId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        if (isListener) {
            setCommentAdapter()
        }
        return super.onCreateView(name, context, attrs)

    }

    private fun setCommentAdapter() {
        if (mAdapter == null) {
            mPostReference = FirebaseUtil.getDatabaseReferenceInPosts()!!
            getPostListener()?.let { mPostReference.addValueEventListener(it) }
            mAdapter = CommentAdapter(this, FirebaseUtil.getDatabaseReferenceInComment())
            rv_postDetail.adapter = mAdapter
        }
    }

    private fun getPostListener(): ValueEventListener? {
        mPostListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("soon2", "post load onCanceled: " + error.message)
                isListener = false
            }

            override fun onDataChange(dataSnapshop: DataSnapshot) {
                Log.v("soon2", "PostDetailActivity onDataChange")
                var post = dataSnapshop.getValue(Post::class.java)
                if (post != null) {
                    setPostContent(post)
                    isListener = true
                }
            }
        }
        return mPostListener
    }

    private fun setPostContent(post: PostModel?) {
        tv_post_category.text = post?.category
        tv_post_title.text = post?.title
        tv_post_location.text = post?.location
        tv_post_price.text = post?.price
        tv_post_date.text = post?.date
        tv_post_time.text = post?.time
        tv_post_body.text = post?.body
        userId = post?.userId.toString()
//        post.author
    }

    override fun onResume() {
        super.onResume()
        setCommentAdapter()
    }

    override fun onStop() {
        super.onStop()
        removeEventListener()
    }

    private fun removeEventListener() {
        if (mPostListener != null) {
            mPostReference.removeEventListener(mPostListener)
            mAdapter.cleanupListener()
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_PostComment -> {
            }
        }
    }

    private fun setPostComment() {
        var reference = FirebaseUtil.getDatabaseReferenceInPostComment(userId)
        reference?.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("soon2", "post comment listener canceled: " + error.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                setCommentModel()
            }

        })
    }

    private fun setCommentModel() {
        var commentText = tv_CommentText?.text.toString()
        var author = User.getNickName()
        var commentModel = CommentModel(userId, author, commentText)
        var mCommentsReference = FirebaseUtil.getDatabaseReferenceInPostComment()
    }

}