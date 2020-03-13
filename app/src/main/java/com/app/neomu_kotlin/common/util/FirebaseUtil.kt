package neomu.kotlin.common.util

import android.app.Activity
import android.os.Debug
import android.util.Log
import android.widget.Toast
import com.app.neomu_kotlin.app.intro.model.PostModel
import com.app.neomu_kotlin.common.constanse.ConstFirebaseDb
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
                ConstFirebaseDb.FIREBASE_DB_CULUMS_USER)
            return databaseRef
        }

        fun getDatabaseReferenceInPosts() : DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POSTS)
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POST_KEY)
            return databaseRef
        }

        fun getDatabaseReferenceInPostChildUser(userId: String): DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_USER)
                .child(userId)
            return databaseRef
        }

        fun getDatabaseReferenceInPostComment(): DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_COMMENT)
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POST_KEY)
            return databaseRef
        }

        fun getDatabaseReferenceInComment() : DatabaseReference? {
            val databaseRef = getDatabaseInstance().getReference()
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_COMMENT)
                .child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POST_KEY)
            return databaseRef
        }

        fun getDatabaseRefInGlobalPost(key: String): DatabaseReference? {
            val ref = getDatabaseReference()
            ref?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_POSTS)
            ref?.child(key)
            return ref
        }

        fun getDatabaseRefInChatUserList(chatName: String): DatabaseReference? {
            val ref = getDatabaseReference()
            ref?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_CHAT)
            ref?.child(chatName)
            ref?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_USERS_LIST)
            return ref
        }

        fun pushUserInRef(userName: String, chatName: String): DatabaseReference? {
            var ref = getDatabaseRefInChatUserList(chatName = chatName)
            ref?.push()
            ref?.setValue(userName)
            return ref
        }

        fun getDatabaseRefInChatName(chatName: String): DatabaseReference? {
            val ref = getDatabaseReference()
            ref?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_CHAT)
            ref?.child(chatName)
            return ref
        }


        fun getDatabaseRefInUserPost(key: String): DatabaseReference? {
            val userPostRef = getDatabaseReference()
            userPostRef?.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_USER_POSTS)
            userPostRef?.child(key)
            return userPostRef
        }

        fun getDatabaseUserName() {
/*            getDatabaseReferenceInUsers()?.orderByChild(ConstFirebaseDb.FIREBASE_DB_CULUMS_EMAIL)
                ?.addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (childDataSnapshot in dataSnapshot.children) {
                            User.nickName = childDataSnapshot.child(ConstFirebaseDb.FIREBASE_DB_CULUMS_NICKNAME).value as String?
                        }
                        User().setNickName(User.nickName)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {}
                })*/
        }

        fun getFirebaseUser() : FirebaseUser? {
            if(mFirebaseAuth != null) {
                mFirebaseAuth = FirebaseAuth.getInstance()
            }
            return mFirebaseAuth.currentUser
        }

        fun getFirebaseUserId() : String? {
            return getFirebaseUser()?.uid.toString()
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

        fun addNewUserInFirebase(post: PostModel) {
            val key = getDatabaseReferenceInPosts()?.push()?.key
            val userMap = post.toMap()
            val mapForUpdate = HashMap<String, Any>()
            mapForUpdate.put(ConstFirebaseDb.UPDATE_CHILD_PATH_POST + key, userMap)
            mapForUpdate.put(ConstFirebaseDb.UPDATE_CHILD_PATH_USER_POST + post.userId + ConstFirebaseDb.UPDATE_CHILD_PATH_SLASH + key, userMap)

            getDatabaseReference()?.updateChildren(mapForUpdate)
        }
    }
}