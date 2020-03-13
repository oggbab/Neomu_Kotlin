package com.app.neomu_kotlin.app.chat.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.chat.model.ChatDTO
import com.app.neomu_kotlin.common.constanse.ConstIntent
import com.app.neomu_kotlin.common.constanse.ConstToast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_chat.*
import neomu.kotlin.common.fragment.BaseFragment
import neomu.kotlin.common.util.FirebaseUtil
import org.jetbrains.anko.support.v4.toast

class ChatFragment : BaseFragment {

    lateinit var USER_NAME: String
    lateinit var ADMIN_NAME: String
    lateinit var CHAT_NAME: String
    lateinit var chatDto: ChatDTO
    lateinit var adapter: ArrayAdapter<Any>

    constructor()
    constructor(bundle: Bundle) {
        CHAT_NAME = bundle.getString(ConstIntent.BUNDLE_CHAT_NAME).toString()
        ADMIN_NAME = bundle.getString(ConstIntent.BUNDLE_ADMIN_NAME).toString()
        USER_NAME = bundle.getString(ConstIntent.BUNDLE_USER_NAME).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArrAdapter()
        openChatWithToast()
        setChatMessage()
    }

    init {
        val chat = et_chatEdit.text.toString()
        chatDto = ChatDTO(CHAT_NAME, chat)
    }

    private fun setArrAdapter() {
        adapter = ArrayAdapter<Any>(
            activity as Context,
            android.R.layout.simple_list_item_1,
            android.R.id.text1
        )
        lv_chatView.adapter = adapter
    }

    private fun openChatWithToast() {
        FirebaseUtil.pushUserInRef(USER_NAME, CHAT_NAME)
            ?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Log.d("soon2", error.toString())
                    toast(ConstToast.MSG_WELLCOME_ERR)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val type = snapshot.toString()
                    when (type) {
                        USER_NAME -> toast(type + ConstToast.MSG_WELLCOME_USER)
                        ADMIN_NAME -> toast(type + ConstToast.MSG_WELLCOME_ADMIN)
                    }
                }
            })
    }

    fun setChatMessage() {
        FirebaseUtil.getDatabaseRefInChatName(CHAT_NAME)?.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("soon2", error.toString())
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousName: String?) {
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousName: String?) {
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousName: String?) {
                addMessage(snapshot)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                removeMessage(snapshot)
            }

        })
    }

    private fun addMessage(snapshot: DataSnapshot) {
        var model = snapshot.getValue(ChatDTO::class.java)
        adapter.add(model?.userName + " : " + model?.message)
    }

    private fun removeMessage(snapshot: DataSnapshot) {
        var model = snapshot.getValue(ChatDTO::class.java)
        adapter.remove(model?.userName + " : " + model?.message)
    }
}
