package com.example.kotlin.snail.kotlinsnailgank.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.snail.kotlinsnailgank.R

import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.ViewHolder
import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidBean

import java.util.ArrayList

/**
 * Created by 张志强 on 2017/7/31.
 */

class AndroidAdapter<T>(mContext: Context) : BaseAdapter<T>(mContext) {

    constructor(data: ArrayList<T>, mContext: Context) : this(mContext)

    override fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int) {
        val androidBean: AndroidBean = t as AndroidBean

        val desc = androidBean.desc
        val createdAt = androidBean.createdAt
        val auth = androidBean.who
        val images = androidBean.images
        var iamgeUrl: String? = images?.get(0)

        (viewMap!![R.id.android_item_desc_tv] as TextView).text = desc
        (viewMap[R.id.android_item_time_tv] as TextView).text = createdAt
        (viewMap[R.id.android_item_auth_tv] as TextView).text = auth
        if (iamgeUrl != null)
            Glide.with(mContext).load(iamgeUrl).placeholder(R.mipmap.preloading).error(R.mipmap.loading_error).into(viewMap[R.id.android_item_iv] as ImageView)
        else
            (viewMap[R.id.android_item_iv] as ImageView).setImageResource(R.mipmap.no_icon)
    }

    override fun getViewResource(viewType: Int): View {
        return layoutInflater.inflate(R.layout.item_android_layout, null)
    }

    override fun addAllViewItems(viewMap: SparseArray<View>, view: View) {
        viewMap.append(R.id.android_item_desc_tv, view.findViewById(R.id.android_item_desc_tv))
        viewMap.append(R.id.android_item_auth_tv, view.findViewById(R.id.android_item_auth_tv))
        viewMap.append(R.id.android_item_time_tv, view.findViewById(R.id.android_item_time_tv))
        viewMap.append(R.id.android_item_iv, view.findViewById(R.id.android_item_iv))
    }


}
