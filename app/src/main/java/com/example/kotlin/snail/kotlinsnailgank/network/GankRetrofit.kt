package com.example.kotlin.snail.kotlinsnailgank.network

import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by 张志强 on 2017/7/31.
 */
class GankRetrofit {

    companion object {
        fun getRetrofit(): Retrofit {
            return Inner.instance
        }

        private object Inner {
            val instance = Retrofit.Builder()
                    .baseUrl(Constant.GankBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(defaultOkHttpClient())
                    .build()!!

            fun defaultOkHttpClient(): OkHttpClient {
                val client = OkHttpClient.Builder()
                        .connectTimeout(3, TimeUnit.SECONDS)
                        .writeTimeout(3, TimeUnit.SECONDS)
                        .readTimeout(3, TimeUnit.SECONDS)
                        .build()
                return client
            }
        }
    }

}