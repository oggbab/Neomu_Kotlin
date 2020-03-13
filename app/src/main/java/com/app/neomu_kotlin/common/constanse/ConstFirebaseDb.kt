package com.app.neomu_kotlin.common.constanse

open class ConstFirebaseDb {

    companion object {
        const val FIREBASE_EXTRAS_LIMIT_START_NUM: Int = 10
        const val FIREBASE_EXTRAS_LIMIT_LAST_NUM: Int = 3
        const val FIREBASE_EXTRAS_START_NUM: Double = 1.0
        const val UPDATE_CHILD_PATH_SLASH = "/"
        const val UPDATE_CHILD_PATH_POST = "/posts/"
        const val UPDATE_CHILD_PATH_USER_POST = "/user-posts/"

        const val FIREBASE_DB_CULUMS_LIKE_COUNT = "likeCount"

        const val FIREBASE_DB_CULUMS_POSTS =    "posts"
        const val FIREBASE_DB_CULUMS_POST_KEY = "post_key"
        const val FIREBASE_DB_CULUMS_COMMENT =  "post-comments"
        const val FIREBASE_DB_CULUMS_USER_POSTS = "user-posts"
        const val FIREBASE_DB_CULUMS_NICKNAME = "nickName"
        const val FIREBASE_DB_CULUMS_USER = "users"
        const val FIREBASE_DB_CULUMS_CHAT = "chat"
        const val FIREBASE_DB_CULUMS_USERS_LIST = "users_list"
    }
}