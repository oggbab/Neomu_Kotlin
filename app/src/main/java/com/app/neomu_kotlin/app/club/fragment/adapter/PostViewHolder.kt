package com.app.neomu_kotlin.app.club.fragment.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.neomu_kotlin.app.intro.model.PostModel

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindToPost(post: PostModel, starClickListener: View.OnClickListener?) {
/*        tv_postTitle.setText(post.title)
        tv_postNumStars.setText(String.valueOf(post.likeCount))
        tv_postBody.setText(post.body)
        tv_postPrice.setText("#" + post.price.toString() + "천원")
        tv_postLocation.setText("#" + "홍대입구역")
        tv_postDate.setText(post.date)
        tv_postTime.setText(post.time)
        tv_postCategory.setText(post.category)
        iv_star.setOnClickListener(starClickListener)*/
    }

    init {
    }
}