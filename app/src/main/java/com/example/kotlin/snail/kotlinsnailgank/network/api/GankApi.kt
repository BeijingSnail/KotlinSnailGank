package com.example.kotlin.snail.kotlinsnailgank.network.api

import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidResult
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
}