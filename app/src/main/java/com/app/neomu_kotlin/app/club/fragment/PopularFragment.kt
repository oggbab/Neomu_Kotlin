package com.app.neomu_kotlin.app.club.fragment

import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb.Companion.FIREBASE_DB_CULUMS_LIKE_COUNT
import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb.Companion.FIREBASE_DB_CULUMS_POSTS
import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb.Companion.FIREBASE_EXTRAS_START_NUM
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

class PopularFragment : MainFragment() {
    fun getQuery(databaseReference: DatabaseReference?): Query? {
        val query = databaseReference
            ?.child(FIREBASE_DB_CULUMS_POSTS)
            ?.orderByChild(FIREBASE_DB_CULUMS_LIKE_COUNT)
            ?.startAt(FIREBASE_EXTRAS_START_NUM)

        return query
    }
}