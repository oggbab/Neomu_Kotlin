package com.app.neomu_kotlin.common.util

import android.app.Activity
import android.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.app.neomu_kotlin.common.constanse.ConstDialog

class CusomAlertDialog {

    lateinit var mActivity: Activity

    constructor(activity: Activity) {
        mActivity = activity
    }

    fun showDialog() : Boolean {
        var result = false

        var dialog = AlertDialog.Builder(mActivity)
            .setTitle(ConstDialog.JOIN_TITLE)
            .setMessage(ConstDialog.JOIN_MSG)
            .setPositiveButton(ConstDialog.JOIN_BTN_POSITIVE) {
                    _, _ -> result = true
            }
            .setNegativeButton(ConstDialog.JOIN_BTN_NEGATIVE){
                    _, _ ->  result = false}
        dialog.show()

        return result
    }
}