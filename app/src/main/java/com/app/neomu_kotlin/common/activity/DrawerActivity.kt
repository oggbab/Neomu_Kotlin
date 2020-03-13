package com.app.neomu_kotlin.common.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.chat.fragment.ChatFragment
import com.app.neomu_kotlin.common.constanse.ConstIntent.Companion.BUNDLE_ADMIN_NAME
import com.app.neomu_kotlin.common.constanse.ConstIntent.Companion.BUNDLE_CHAT_NAME
import com.app.neomu_kotlin.common.constanse.ConstIntent.Companion.BUNDLE_USER_NAME
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.tool_bar_chat.*

class DrawerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setCustomToolbar()
        replaceFragment(
            ChatFragment(
                getBundleFromIntent()
            ), R.id.fr_chat)
    }

    override fun onStart() {
        super.onStart()

//        todo 네비게이션 뷰 새로 만들기(androidX 에서 material 미지원)
/*        nv_main.setNavigationItemSelectedListener(object : OnNavigationItemSelectedListener() {
            fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.first -> {
                        val intent =
                            Intent(this@ChatActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        drawer_chat.closeDrawer(GravityCompat.START)
                    }
                    R.id.second -> {
                        intent = Intent(this@ChatActivity, MypageActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        drawer_chat.closeDrawer(GravityCompat.START)
                    }
                    R.id.third -> {
                        //미구현 상태
                        showToast("즐겨찾기 설정해요", false)
                        drawer_chat.closeDrawer(GravityCompat.START)
                    }
                    R.id.fourth -> {
                        intent = Intent(this@ChatActivity, MapyActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                    R.id.fifth -> {
                        intent = Intent(this@ChatActivity, Club_New_Activity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                }
                return false
            }
        })*/
    }

    private fun getBundleFromIntent(): Bundle {
        var intent: Intent = getIntent()
        var bundle : Bundle = Bundle()
        if (intent != null) {
            bundle.also {
                it.putString(BUNDLE_CHAT_NAME, intent.getStringExtra(BUNDLE_CHAT_NAME))
                it.putString(BUNDLE_USER_NAME, intent.getStringExtra(BUNDLE_USER_NAME))
                it.putString(BUNDLE_ADMIN_NAME, intent.getStringExtra(BUNDLE_ADMIN_NAME))
            }
        }
        return bundle
    }

    fun setCustomToolbar() {
        setSupportActionBar(toolbar_chat)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_chat.setNavigationIcon(R.drawable.ic_menu_black_24dp)

        val actionBarToggle = ActionBarDrawerToggle(this, drawer_chat, toolbar_chat,0,0)
        drawer_chat.setDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
    }

}