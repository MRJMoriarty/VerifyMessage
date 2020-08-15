package com.example.phonenumber

import android.app.Application
import cn.bmob.v3.Bmob

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化Bmob的SDK
        Bmob.initialize(this, "d714a02ad07ef0a11f1f35e93be71c9a")
    }
}