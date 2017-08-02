package com.example.kotlin.snail.kotlinsnailgank

import android.app.Application

/**
 * 自定义 Application 类
 * Created by snial on 2017/5/23.
 */

class GankApp : Application() {

    companion object {
        lateinit var instance: GankApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
