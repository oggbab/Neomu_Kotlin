package com.app.neomu_kotlin.app

import android.app.Application

class NeoMuApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mApp = this
    }

    companion object {
        lateinit var mApp: NeoMuApp

        fun getApp(): NeoMuApp {
            return mApp
        }
    }
}