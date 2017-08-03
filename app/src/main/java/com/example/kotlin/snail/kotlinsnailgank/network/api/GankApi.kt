package com.example.kotlin.snail.kotlinsnailgank.network.api

import com.example.kotlin.snail.kotlinsnailgank.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by 张志强 on 2017/7/31.
 */
interface GankApi {
    /**
     * Android所有数据
     */
    @GET("api/data/Android/10/{page}")
    fun getAndroidDatas(@Path("page") page: Int): Observable<JsonResult<List<DataBean>>>

    /**
     * iOS所有数据
     */
    @GET("api/data/iOS/10/{page}")
    fun getIosdDatas(@Path("page") page: Int): Observable<JsonResult<List<DataBean>>>

    /**
     * 福利
     */
    @GET("api/data/福利/10/{page}")
    fun getWelfareDatas(@Path("page") page: Int): Observable<JsonResult<List<DataBean>>>

    /**
     * 拓展资源
     */
    @GET("api/data/拓展资源/10/{page}")
    fun getResDatas(@Path("page") page: Int): Observable<JsonResult<List<DataBean>>>
}