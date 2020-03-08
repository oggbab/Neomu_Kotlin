package com.app.neomu_kotlin.app.club.fragment

import com.app.neomu_kotlin.common.constanse.FirebaseDbConstance
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import neomu.kotlin.common.util.FirebaseUtil

class NewFragment : MainFragment() {

    fun getQuery(databaseReference: DatabaseReference?): Query? {
        val query = FirebaseUtil.getFirebaseUserId()?.let {
            databaseReference
                ?.child(FirebaseDbConstance.FIREBASE_DB_CULUMS_USER_POSTS)
                ?.child(it)
                ?.limitToLast(FirebaseDbConstance.FIREBASE_EXTRAS_LIMIT_LAST_NUM)
        }
        return query
    }
}