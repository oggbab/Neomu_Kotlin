package com.app.neomu_kotlin.app.intro.model

import com.google.firebase.database.Exclude
import java.util.HashMap

data class PostModel(
    var userId: String,
    var author: String,
    var title: String,
    var body: String,
    var location: String,
    var category: String,
    var bg_img: String,
    var price: String,
    var date: String,
    var time: String,
    var likeCount: Int = 0
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        val result = HashMap<String, Any?>()
        result["userId"] = userId
        result["author"] = author
        result["title"] = title
        result["body"] = body
        result["location"] = location
        result["category"] = category
        result["bg_img"] = bg_img
        result["price"] = price
        result["date"] = date
        result["time"] = time
        result["likeCount"] = likeCount

        return result
    }
}


