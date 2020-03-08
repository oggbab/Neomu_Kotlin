package com.app.neomu_kotlin.app.intro.model

import android.bluetooth.BluetoothHidDeviceAppSdpSettings

data class PostModel (
    var userId: String,
    var author: String,
    var title: String,
    var body: String,
    var location: String,
    var category: String,
    var bg_img: String,
    var price: String,
    var date: String,
    var time: String,
    var likeCount: Int
    )