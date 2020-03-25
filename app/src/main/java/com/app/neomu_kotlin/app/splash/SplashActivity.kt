package neomu.kotlin.app.splash

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.activity.MainActivity
import com.app.neomu_kotlin.common.activity.BaseActivity
import neomu.kotlin.app.main.LoginActivity
import neomu.kotlin.common.util.FirebaseUtil
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        startActivity<LoginActivity>()
    }
}