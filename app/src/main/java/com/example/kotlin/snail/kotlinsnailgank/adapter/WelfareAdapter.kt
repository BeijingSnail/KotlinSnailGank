package com.example.kotlin.snail.kotlinsnailgank.adapter

import android.content.Context
import android.text.TextUtils
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.bean.WelfareBean

/**
 * Created by 张志强 on 2017/7/31.
 */
class WelfareAdapter<T>(context: Context) : BaseAdapter<T>(context) {
    override fun addAllViewItems(viewMap: SparseArray<View>, view: View) {
        viewMap.put(R.id.welfare_item_iv, view.findViewById(R.id.welfare_item_iv))
        viewMap.put(R.id.welfare_auth_tv, view.findViewById(R.id.welfare_auth_tv))
        viewMap.put(R.id.welfare_time_tv, view.findViewById(R.id.welfare_time_tv))
    }

    override fun getViewResource(viewType: Int): View {
        return layoutInflater.inflate(R.layout.item_welfare_layout, null)
    }

    override fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int) {
        var welfareBean: WelfareBean = t as WelfareBean
        (viewMap!![R.id.welfare_time_tv] as TextView).text = welfareBean.createdAt
        (viewMap!![R.id.welfare_auth_tv] as TextView).text = welfareBean.who
        var iamgeUrl = welfareBean.url
        if (!TextUtils.isEmpty(iamgeUrl)) {
            Glide.with(mContext).load(iamgeUrl).placeholder(R.mipmap.preloading).error(R.mipmap.loading_error).into(viewMap!![R.id.welfare_item_iv] as ImageView)
        }
    }
}