package com.app.neomu_kotlin.app.club.fragment

import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query

class NearFragment : MainFragment() {

    fun getQuery(databaseReference: DatabaseReference?): Query? {
        val query = databaseReference
            ?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POSTS)
            ?.limitToFirst(ConstFirebaseDb.FIREBASE_EXTRAS_LIMIT_START_NUM)

        return query
    }
}