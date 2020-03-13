package com.app.neomu_kotlin.app.club.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import com.app.neomu_kotlin.R
import com.app.neomu_kotlin.app.intro.model.PostModel
import com.app.neomu_kotlin.common.constanse.ConstNetwork
import com.not.app.map.MapyActivity
import kotlinx.android.synthetic.main.activity_club.*
import kotlinx.android.synthetic.main.fragment_club.*
import neomu.kotlin.common.fragment.BaseFragment
import neomu.kotlin.common.util.FirebaseUtil
import java.util.*

class ClubFragment : BaseFragment() {

    lateinit var mActivity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this!!.activity!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_club, null)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.et_club_location -> {
                val intent = Intent(activity, MapyActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivityForResult(intent, ConstNetwork.INTENT_REQUEST_CODE)
            }

            R.id.btn_club_new -> {
                val model = getModelForAddNewUser()
                FirebaseUtil.addNewUserInFirebase(model)
            }

            R.id.btn_club_time -> {
                showTimePickerDialog()
            }
        }
    }

    private fun getModelForAddNewUser(): PostModel {
        val userId = FirebaseUtil.getFirebaseUserId().toString()
        val title = et_club_title.text.toString()
        val body = et_club_intro.text.toString()
        val location = et_club_location.text.toString()
        val category = getCheckedCategory()
        val price = tv_club_price.text.toString()
        val model = PostModel(userId, author = "", title = title, body = body, location = location,
            category = category, bg_img= "", price = price, date = "", time = "", likeCount = 0)

        return model
    }

    private fun getCheckedCategory(): String {
        val rg_01 = mActivity?.findViewById<RadioGroup>(R.id.rg_01)
        val rg_02 = mActivity.findViewById<RadioGroup>(R.id.rg_02)
        var checkedCategory = ""

        rg_01.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                checkedCategory = mActivity.findViewById<RadioButton>(checkedId)?.text.toString()
            }
        })

        rg_02.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                checkedCategory = mActivity.findViewById<RadioButton>(checkedId)?.text.toString()
            }
        })

        return checkedCategory
    }


    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        var dialog = DatePickerDialog(mActivity, object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, date: Int) {
                val msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date)
                tv_club_date.text = msg
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),  calendar.get(Calendar.DATE))

        dialog.show()
    }

}