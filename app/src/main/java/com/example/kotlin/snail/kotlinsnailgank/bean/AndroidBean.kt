package com.example.kotlin.snail.kotlinsnailgank.bean

/**
 * Created by admin on 2017/5/20.
 */

class AndroidBean {
    var _id: String? = null

    var createdAt: String? = null

    var desc: String? = null

    var images: List<String>? = null

    var publishedAt: String? = null

    var source: String? = null

    var type: String? = null

    var url: String? = null

    var used: Boolean = false

    var who: String? = null

    override fun toString(): String {
        return "AndroidBean{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", images=" + images +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}'
    }
}
