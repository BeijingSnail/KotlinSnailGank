package com.example.kotlin.snail.kotlinsnailgank.bean

/**
 * Created by snail on 2017/5/23.
 */

class IosBean {

    var id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var images: List<String>? = null
    var publishedat: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    var used: Boolean = false
    var who: String? = null

    override fun toString(): String {
        return "IosBean{" +
                "_id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", images=" + images +
                ", publishedat='" + publishedat + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}'
    }
}
