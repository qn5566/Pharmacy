package com.meetstudio.pharmacy

import android.app.Application

class CustomApplication : Application() {

    private lateinit var mInstance : CustomApplication


    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

}