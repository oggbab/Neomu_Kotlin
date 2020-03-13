package com.app.neomu_kotlin.app.club.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb
import com.app.neomu_kotlin.common.util.ClickListenerImpl
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.not.app.models.Post
import com.not.app.viewholder.PostViewHolder
import neomu.kotlin.common.util.FirebaseUtil

class FirebaseAdapter() :
    FirebaseRecyclerAdapter<Post, PostViewHolder>(FirebaseUtil.getFirebaseRecyclerOption()) {

    constructor(listener: ClickListenerImpl) : this() {
        this.listener = listener
    }

    lateinit var listener: ClickListenerImpl

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val postViewHolder = PostViewHolder(inflater.inflate(R.layout.item_post, viewGroup, false))
        return postViewHolder
    }

    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int, model: Post) {
        val postReference = getRef(position)
        val postKey = postReference.key

        viewHolder.itemView.setOnClickListener() {
            listener.onClicked()
        }

        viewHolder.bindToPost(model) {
            onStarClicked()
        }
    }

    private fun onStarClicked() {
        FirebaseUtil.getDatabaseRefInGlobalPost(
            ConstFirebaseDb.FIREBASE_DB_CULUMS_POSTS
        )?.let {
            onTransaction(it)
        }

        FirebaseUtil.getDatabaseRefInUserPost(
            ConstFirebaseDb.FIREBASE_DB_CULUMS_USER_POSTS
        )?.let {
            onTransaction(it)
        }
    }

    private fun onTransaction(postRef: DatabaseReference) {
        postRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(data: MutableData): Transaction.Result {
                var post = data.getValue(Post::class.java)
                val userId = FirebaseUtil.getFirebaseUserId()
                if (post?.like?.containsKey(userId)!!) {
                    post.likeCount = post.likeCount - 1
                    post.like.remove(userId)
                } else {
                    post.likeCount = post.likeCount + 1
                    post.like.put(userId, true)
                }
                data.value = post

                return Transaction.success(data)
            }

            override fun onComplete(p0: DatabaseError?, p1: Boolean, p2: DataSnapshot?) {

            }
        })
    }
}