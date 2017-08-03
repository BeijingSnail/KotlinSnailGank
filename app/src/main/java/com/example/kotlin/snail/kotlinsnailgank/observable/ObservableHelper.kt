package com.example.kotlin.snail.kotlinsnailgank.observable

import android.text.TextUtils
import com.example.kotlin.snail.kotlinsnailgank.bean.*
import com.example.kotlin.snail.kotlinsnailgank.common.ANDROID
import com.example.kotlin.snail.kotlinsnailgank.common.IOS
import com.example.kotlin.snail.kotlinsnailgank.common.RES
import com.example.kotlin.snail.kotlinsnailgank.common.WELFARE
import com.example.kotlin.snail.kotlinsnailgank.network.GankRetrofit
import com.example.kotlin.snail.kotlinsnailgank.network.api.GankApi

import rx.Observable
import rx.functions.Func1

/**
 * Created by Snail on 2017/7/31
 */

object ObservableHelper {

    fun getDataObservable(type: String, page: Int): Observable<List<DataBean>> {
        val creat = GankRetrofit.getRetrofit().create(GankApi::class.java)
        when (type) {
            ANDROID -> return creat.getAndroidDatas(page).flatMap({ jsonResult -> getDataList(jsonResult) })
            IOS -> return creat.getIosdDatas(page).flatMap({ jsonResult -> getDataList(jsonResult) })
            WELFARE -> return creat.getWelfareDatas(page).flatMap({ jsonResult -> getDataList(jsonResult) })
            RES -> return creat.getResDatas(page).flatMap({ jsonResult -> getDataList(jsonResult) })
            else -> return creat.getAndroidDatas(page).flatMap({ jsonResult -> getDataList(jsonResult) })
        }
    }

    private fun getDataList(jsonResult: JsonResult<List<DataBean>>): Observable<List<DataBean>> {
        val androidBeanList = jsonResult.results
        for (bean in androidBeanList!!) {
            bean.createdAt = formatCreatedAt(bean.createdAt!!)
        }

        return Observable.create<List<DataBean>> { subscriber ->
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
