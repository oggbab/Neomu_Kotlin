package com.app.neomu_kotlin.app.chat.activity

import android.os.Bundle
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.chat.fragment.ChatFragment
import com.app.neomu_kotlin.common.activity.BaseActivity

class ChatActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        //todo 진입시 bundle 어디서 받는
        replaceFragment(ChatFragment(), R.id.fr_chat)
    }
}