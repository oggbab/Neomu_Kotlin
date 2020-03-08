package com.app.neomu_kotlin.app.club.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.club.fragment.adapter.FirebaseAdapter
import com.app.neomu_kotlin.common.constanse.FirebaseDbConstance
import com.app.neomu_kotlin.common.util.ClickListenerImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.not.app.club.Club_New_Activity
import kotlinx.android.synthetic.main.fragment_main.*
import neomu.kotlin.common.fragment.BaseFragment
import org.jetbrains.anko.support.v4.intentFor

open class MainFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLayoutManager()
        setAdapter()
    }

    private fun setLayoutManager() {
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_main.layoutManager = layoutManager
    }

    private fun setAdapter() {
        val adapter = FirebaseAdapter(object : ClickListenerImpl{
            override fun onClicked() {
                startActivity(intentFor<Club_New_Activity>().addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
            }
        })
        rv_main.setHasFixedSize(true)
        rv_main.adapter = adapter
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_floating -> {
                startActivity(intentFor<Club_New_Activity>().addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
            }
        }
    }
}