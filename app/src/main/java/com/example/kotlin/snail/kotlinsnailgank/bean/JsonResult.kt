package com.example.kotlin.snail.kotlinsnailgank.bean

/**
 * Created by 张志强 on 2017/8/3.
 */
class JsonResult<T> {
    var error: Boolean = false
    var results: T? = null
}