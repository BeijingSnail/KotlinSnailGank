package com.example.kotlin.snail.kotlinsnailgank.observable

import android.text.TextUtils
import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidBean
import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidResult
import com.example.kotlin.snail.kotlinsnailgank.network.GankRetrofit
import com.example.kotlin.snail.kotlinsnailgank.network.api.GankApi

import rx.Observable

/**
 * Created by Snail on 2017/7/31
 */

object ObservableHelper {

    fun getAndroidObservable(type: String, count: Int, page: Int): Observable<List<AndroidBean>> {

        return GankRetrofit.getRetrofit()
                .create(GankApi::class.java)
                .getAndroidDatas(type, count, page)
                .flatMap({ androidResult -> getAndroidList(androidResult) })
    }


    private fun getAndroidList(androidResult: AndroidResult): Observable<List<AndroidBean>> {
        val androidBeanList = androidResult.results
        for (bean in androidBeanList!!) {
            bean.createdAt = formatCreatedAt(bean.createdAt!!)
        }

        return Observable.create<List<AndroidBean>> { subscriber ->
            subscriber.onNext(androidBeanList)
            subscriber.onCompleted()
        }
    }


    private fun formatCreatedAt(createdAt: String): String {
        if (TextUtils.isEmpty(createdAt)) {
            return ""
        }
        return createdAt.substring(0, createdAt.indexOf("T"))
    }

}
