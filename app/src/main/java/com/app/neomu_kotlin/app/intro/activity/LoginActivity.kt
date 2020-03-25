package neomu.kotlin.app.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.activity.MainActivity
import com.app.neomu_kotlin.common.activity.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import neomu.kotlin.common.util.FirebaseUtil
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    lateinit var forInvalidChecklist: MutableList<String>
    lateinit var accountTextMap: MutableMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login_m -> {
                signInFirebaseAuth()

                /*ValidationUtil.isValidate(forInvalidChecklist, object : ValidationUtilListener {
                    override fun onInvalid() {

                    }

                    override fun onSuccess() {
                        signInFirebaseAuth()
                    }

                })
 */
            }
            R.id.tv_login_link -> {
//                signInFirebaseAuth()
testFb()

//                startActivity(intentFor<AuthActivity>("logTest" to "onNewIntent() 실행")
//                                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
            }
        }
    }

    fun testFb() {
        val mAuth = FirebaseAuth.getInstance()

        val id = "oggbab@nate.com"
        val pw = "wjddyddnd1"

        mAuth.createUserWithEmailAndPassword(id, pw)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this,"join complete", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("soon2", it.toString())
                    Toast.makeText(this,"join fail", Toast.LENGTH_SHORT).show()
                }
            }

//        var result = false
//        mAuth.signInWithEmailAndPassword(id, pw)
//            .addOnCompleteListener(this) {
//                Toast.makeText(this,"sign complete", Toast.LENGTH_SHORT).show()
//                if (it.isSuccessful) {
//                    result = true
//                } else {
//                }
//            }
    }

    private fun signInFirebaseAuth() {
        val result =
            FirebaseUtil.isSuccessSignInFirebaseAuth(this, getUserIdForLogin(), getUserPwForLogin())
        if (result) {
            startActivity<MainActivity>()
        } else {
            toast("fail login")
        }
    }

    private fun getAccountForLogin(): MutableMap<String, String> {
        accountTextMap =
            mutableMapOf("userId" to getUserIdForLogin(), "userPw" to getUserPwForLogin())
        return accountTextMap
    }

    private fun getUserIdForLogin(): String {
        val name = findViewById<EditText>(R.id.et_login_id)?.text.toString()
        return if (name.isNullOrEmpty()) "" else name
    }

    private fun getUserPwForLogin(): String {
        val password = findViewById<EditText>(R.id.et_login_pw)?.text.toString()
        return if (password.isNullOrEmpty()) "" else password
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        toast(intent?.getStringExtra("logTest").toString())
    }
}