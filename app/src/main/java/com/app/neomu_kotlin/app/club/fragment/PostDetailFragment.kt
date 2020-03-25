package com.app.neomu_kotlin.app.club.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.activity.PostDetailActivity
import com.app.neomu_kotlin.app.club.fragment.adapter.CommentAdapter
import com.app.neomu_kotlin.app.intro.model.CommentModel
import com.app.neomu_kotlin.app.intro.model.PostModel
import com.app.neomu_kotlin.app.main.model.UserInfoModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_post_detail.*
import kotlinx.android.synthetic.main.include_post_author.*
import kotlinx.android.synthetic.main.include_post_date.*
import kotlinx.android.synthetic.main.include_post_infor.*
import kotlinx.android.synthetic.main.include_post_text.*
import neomu.kotlin.common.fragment.BaseFragment
import neomu.kotlin.common.util.FirebaseUtil
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor
import java.util.zip.Inflater

class PostDetailFragment : BaseFragment(){


    private lateinit var mPostListener: ValueEventListener
    private lateinit var mPostReference: DatabaseReference
    private var isListener: Boolean = false
    private lateinit var mAdapter: CommentAdapter
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_post_detail, null)
    }


    init {
        if (isListener) {
            setCommentAdapter()
        }
        setPostComment()
    }

    private fun setCommentAdapter() {
        if (mAdapter == null) {
            mPostReference = FirebaseUtil.getDatabaseReferenceInPosts()!!
            getPostListener()?.let { mPostReference.addValueEventListener(it) }
            mAdapter = CommentAdapter(activity, FirebaseUtil.getDatabaseReferenceInComment())
            rv_post_detail.adapter = mAdapter
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
                var post = dataSnapshop.getValue(PostModel::class.java)
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
        when (view?.id) {
            R.id.btn_post_comment -> {
                setPostComment()
            }

            R.id.btn_post_ㅓoin -> {
                //todo 파라미터 가져오는 값 변경
                var chatName = btn_post_ㅓoin.text.toString()
                var adminName = btn_post_ㅓoin.text.toString()
                var userName = btn_post_ㅓoin.text.toString()
                startActivity(intentFor<PostDetailActivity>(
                    "chatName" to chatName, "adminName" to adminName, "userName" to userName))
            }
        }
    }

    private fun setPostComment() {
        var reference = FirebaseUtil.getDatabaseReferenceInPostChildUser(userId)
        reference?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("soon2", "post comment listener canceled: " + error.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                setCommentModel()
            }

        })
    }

    private fun setCommentModel() {
        var commentText = et_comment.text.toString()
//        var author = UserInfoModel.nickNma()
//        var commentModel = CommentModel(userId, author, commentText)
//        var mCommentsReference = FirebaseUtil.getDatabaseReferenceInPostComment()
//        mCommentsReference?.push()?.setValue(commentModel)
        et_comment.text = null
    }


}