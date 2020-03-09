package com.app.neomu_kotlin.app.intro.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
class Post {
    var uid: String? = null
    var author: String? = null
    var title: String? = null
    var body: String? = null
    var location: String? = null
    var category: String? = null
    var bg_img: String? = null
    var price: String? = null
    var date: String? = null
    var time: String? = null
    var likeCount: Long = 0
    var like: Map<String, Boolean> =
        HashMap()

    constructor(
        uid: String?,
        author: String?,
        title: String?,
        body: String?,
        price: String?,
        category: String?,
        location: String?,
        date: String?,
        time: String?
    ) {
        this.uid = uid
        this.author = author
        this.title = title
        this.body = body
        this.price = price
        this.category = category
        this.location = location
        this.date = date
        this.time = time
        /*
        this.bg_img = bg_img;*/ㅁㄴㅇ
    }

    // 맵형태로 넣기
    @Exclude
    fun toMap(): Map<String, Any?> {
        val result =
            HashMap<String, Any?>()
        result["title"] = title
        result["body"] = body
        result["uid"] = uid
        result["author"] = author
        result["likeCount"] = likeCount
        result["like"] = like
        result["price"] = price
        result["category"] = category
        result["location"] = location
        result["date"] = date
        result["time"] = time
        /*
        result.put("bg_img", bg_img);*/return result
    }
}