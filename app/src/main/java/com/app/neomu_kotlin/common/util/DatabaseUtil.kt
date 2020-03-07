package com.app.neomu_kotlin.common.util

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

open class DatabaseUtil {
    lateinit var mDatabaseReference: DatabaseReference

    fun getDatabaseReference() : DatabaseReference? {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
        return mDatabaseReference
    }

    fun getPostQuery(): Query {
        val postQuery = getDatabaseReference() as Query
        return postQuery
    }
}