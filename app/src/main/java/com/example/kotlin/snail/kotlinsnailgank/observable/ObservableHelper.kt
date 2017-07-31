package com.example.kotlin.snail.kotlinsnailgank.observable

import android.text.TextUtils
import com.example.kotlin.snail.kotlinsnailgank.bean.*
import com.example.kotlin.snail.kotlinsnailgank.network.GankRetrofit
import com.example.kotlin.snail.kotlinsnailgank.network.api.GankApi

import rx.Observable
import rx.functions.Func1

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

    fun getIosObservable(type: String, count: Int, page: Int): rx.Observable<List<IosBean>> {
        return GankRetrofit.getRetrofit()
                .create(GankApi::class.java)
                .getIosdDatas(type, count, page)
                .flatMap({ iosResult -> getIosList(iosResult) })
    }

    fun getWelfareObservable(type: String, count: Int, page: Int): rx.Observable<List<WelfareBean>> {
        return GankRetrofit.getRetrofit()
                .create(GankApi::class.java)
                .getWelfareDatas(type, count, page)
                .flatMap({ welfareBean -> getWelfareList(welfareBean) })
    }

    fun getResObservable(type: String, count: Int, page: Int): Observable<List<ResBean>> {
        return GankRetrofit.getRetrofit()
                .create(GankApi::class.java)
                .getResDatas(type, count, page)
                .flatMap({ resBean -> getResList(resBean) })
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

    private fun getIosList(iosResult: IosResult): Observable<List<IosBean>> {
        val iosBeanList = iosResult.results
        for (iosBean in iosBeanList!!) {
            iosBean.createdAt = formatCreatedAt(iosBean.createdAt!!)
        }

        return Observable.create<List<IosBean>> { subscriber ->
            subscriber.onNext(iosBeanList)
            subscriber.onCompleted()
        }
    }

    private fun getWelfareList(welfareResult: WelfareResult): Observable<List<WelfareBean>> {
        val welfareBeanList = welfareResult.results
        for (welfareBean in welfareBeanList!!) {
            welfareBean.createdAt = formatCreatedAt(welfareBean.createdAt!!)
        }
        return Observable.create<List<WelfareBean>> { subscriber ->
            subscriber.onNext(welfareBeanList)
            subscriber.onCompleted()
        }
    }

    private fun getResList(resResult: ResResult): Observable<List<ResBean>> {
        val resBeanList = resResult.results
        for (resBean in resBeanList!!) {
            resBean.createdAt = formatCreatedAt(resBean.createdAt!!)
        }
        return Observable.create<List<ResBean>> { subscriber ->
            subscriber.onNext(resBeanList)
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
