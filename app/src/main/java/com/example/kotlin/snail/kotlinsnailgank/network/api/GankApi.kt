package com.example.kotlin.snail.kotlinsnailgank.network.api

import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidResult
import com.example.kotlin.snail.kotlinsnailgank.bean.IosResult
import com.example.kotlin.snail.kotlinsnailgank.bean.ResResult
import com.example.kotlin.snail.kotlinsnailgank.bean.WelfareResult
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by 张志强 on 2017/7/31.
 */
interface GankApi {
    @GET("api/data/{type}/{count}/{page}")
    fun getAndroidDatas(
            @Path("type") type: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Observable<AndroidResult>


    @GET("api/data/{type}/{count}/{page}")
    abstract fun getIosdDatas(
            @Path("type") type: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Observable<IosResult>

    @GET("api/data/{type}/{count}/{page}")
    abstract fun getWelfareDatas(
            @Path("type") type: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Observable<WelfareResult>

    @GET("api/data/{type}/{count}/{page}")
    abstract fun getResDatas(
            @Path("type") type: String,
            @Path("count") count: Int,
            @Path("page") page: Int
    ): Observable<ResResult>
}