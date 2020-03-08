package neomu.kotlin.common.util

import android.app.Activity
import com.app.neomu_kotlin.common.constanse.FirebaseDbConstance
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.remoteconfig.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.not.app.models.Post
import com.not.app.models.User


open class FirebaseUtil {


    companion object {

        private lateinit var mFirebaseRemoteConfig: FirebaseRemoteConfig
        private lateinit var mFirebaseAuth: FirebaseAuth
        private lateinit var mDatabaseReference: DatabaseReference
        private lateinit var mFirebaseDatabase: FirebaseDatabase

        fun getPostQuery(): Query {
            val postQuery = getDatabaseReference() as Query
            return postQuery
        }

        fun getDatabaseInstance() : FirebaseDatabase {
            if (mFirebaseDatabase == null) {
                mFirebaseDatabase = FirebaseDatabase.getInstance()
            }
            return mFirebaseDatabase
        }

        fun getDatabaseReference() : DatabaseReference? {
            if (mDatabaseReference == null) {
                mDatabaseReference = getDatabaseInstance().getReference()
            }
            return mDatabaseReference
        }

        fun getDatabaseReferenceInUsers() : DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference(
                FirebaseDbConstance.FIREBASE_DB_CULUMS_USER)
            return databaseRef
        }

        fun getDatabaseReferenceInPosts() : DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(FirebaseDbConstance.FIREBASE_DB_CULUMS_POSTS)
                .child(FirebaseDbConstance.FIREBASE_DB_CULUMS_POST_KEY)
            return databaseRef
        }

        fun getDatabaseReferenceInPostComment(userId: String): DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(FirebaseDbConstance.FIREBASE_DB_CULUMS_USER)
                .child(userId)
            return databaseRef
        }

        fun getDatabaseReferenceInComment() : DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(FirebaseDbConstance.FIREBASE_DB_CULUMS_COMMENT)
                .child(FirebaseDbConstance.FIREBASE_DB_CULUMS_POST_KEY)
            return databaseRef
        }

        fun getDatabaseUserName() {
            getDatabaseReferenceInUsers()?.orderByChild(FirebaseDbConstance.FIREBASE_DB_CULUMS_EMAIL)
                ?.addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (childDataSnapshot in dataSnapshot.children) {
                            User.nickName = childDataSnapshot.child(FirebaseDbConstance.FIREBASE_DB_CULUMS_NICKNAME).value as String?
                        }
                        User().setNickName(User.nickName)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })
        }

        fun getFirebaseUser() : FirebaseUser? {
            if(mFirebaseAuth != null) {
                mFirebaseAuth = FirebaseAuth.getInstance()
            }
            return mFirebaseAuth.currentUser
        }

        fun getFirebaseUserId() : String? {
            return getFirebaseUser()?.uid
        }

        fun getFirebaseUserEmail() : String? {
            return getFirebaseUser()?.email
        }

        fun getFirebaseRecyclerOption(): FirebaseRecyclerOptions<Post> {
            val postQuery = getPostQuery()
            val options = FirebaseRecyclerOptions.Builder<Post>()
                    .setQuery(postQuery, Post::class.java)
                    .build()
            return options
        }

        fun setFirebaseRemoteConfig() {
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            val configSetting = FirebaseRemoteConfigSettings.Builder()
                    .setDeveloperModeEnabled(BuildConfig.DEBUG)
                    .build()
            mFirebaseRemoteConfig.setConfigSettingsAsync(configSetting)
    /*        mFirebaseRemoteConfig
                    .fetch(0)
                    .addOnCompleteListener(this, OnCompleteListener<Void?> { task ->
                        if (task.isSuccessful) {
                            mFirebaseRemoteConfig.activateFetched()
                        }
                        showDialogMessage()
                    })*/
        }

        fun showDialogMessage(activity: Activity) {

            val splashBg = mFirebaseRemoteConfig.getString("Splash_background")
            val splashCaps = mFirebaseRemoteConfig.getBoolean("Splash_message_caps")
            val splashMessage = mFirebaseRemoteConfig.getString("Splash_message")

            if (splashCaps) {
    //            val builder = AlertDialog.Builder(this)
    //            builder.setMessage(Splash_message).setPositiveButton(getResources().getString("text_splash_btn_text".toInt()), DialogInterface.OnClickListener { dialog, which -> })
    //            builder.create().show()
            } else { //caps가 false일경우 액티비티 이동
    //            val intent = Intent(activity@ Spl/ashActivity, LoginActivity::class.java)
    //            startActivity<MainActivity>{}
    //            finish()
            }
        }


        fun getDatabaseRefInGlobalPost(key: String): DatabaseReference? {
            val globalPostRef = getDatabaseReference()
            globalPostRef?.child(FirebaseDbConstance.FIREBASE_DB_CULUMS_POSTS)
            globalPostRef?.child(key)
            return globalPostRef
        }

        fun getDatabaseRefInUserPost(key: String): DatabaseReference? {
            val userPostRef = getDatabaseReference()
            userPostRef?.child(FirebaseDbConstance.FIREBASE_DB_CULUMS_USER_POSTS)
            userPostRef?.child(key)
            return userPostRef
        }

    }
}