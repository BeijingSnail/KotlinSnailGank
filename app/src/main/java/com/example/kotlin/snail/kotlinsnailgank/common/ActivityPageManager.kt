package com.example.kotlin.snail.kotlinsnailgank.common

import android.app.Activity
import java.util.*

/**
 * Created by 张志强 on 2017/7/28.
 */
class ActivityPageManager {
    private var activityStack: Stack<Activity>? = null

    //kotlind单例
    companion object {
        fun getInstance(): ActivityPageManager {
            return Inner.instance
        }
    }

    private object Inner {
        val instance = ActivityPageManager()
    }

    /**
     * 向Stack中添加Activity
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 从Stack中移除Activity
     */
    fun removeActivity(activity: Activity) {
        activityStack!!.remove(activity)
    }

    /**
     * 获取当前Activity
     */
    fun getCurrentActivity() = activityStack!!.lastElement()


    /**
     * finish指定Activity
     */
    private fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity == cls) {
                finishActivity(activity)
                break
            }

        }
    }

    private fun finishActivity(activirty: Activity) {
        activityStack!!.remove(activirty)
        activirty.finish()
    }

    /**
     * 关闭所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityStack!!) {
            activity.finish()
        }
        activityStack!!.clear()
    }

    /**
     * 完全退出程序
     */
    fun exit() {
        finishAllActivity()
        System.exit(0)
        android.os.Process.killProcess(android.os.Process.myPid())
    }


}