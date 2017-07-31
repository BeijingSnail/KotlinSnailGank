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
import com.example.kotlin.snail.kotlinsnailgank.bean.IosBean

/**
 * Created by 张志强 on 2017/7/31.
 */
class IosAdapter<T>(context: Context) : BaseAdapter<T>(context) {
    override fun addAllViewItems(viewMap: SparseArray<View>, view: View) {
        viewMap.append(R.id.ios_desc_tv, view.findViewById(R.id.ios_desc_tv))
        viewMap.append(R.id.ios_item_iv, view.findViewById(R.id.ios_item_iv))
        viewMap.append(R.id.ios_item_auth_tv, view.findViewById(R.id.ios_item_auth_tv))
        viewMap.append(R.id.ios_item_time_tv, view.findViewById(R.id.ios_item_time_tv))
    }

    override fun getViewResource(viewType: Int): View {
        return layoutInflater.inflate(R.layout.item_ios_layout, null)
    }

    override fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int) {
        val iosBean: IosBean = t as IosBean
        val desc = iosBean.desc
        val creatAt = iosBean.createdAt
        val auth = iosBean.who
        val images = iosBean.images
        var imageUrl = images?.get(0)
        (viewMap!![R.id.ios_desc_tv] as TextView).text = desc
        (viewMap[R.id.ios_item_time_tv] as TextView).text = creatAt
        (viewMap[R.id.ios_item_auth_tv] as TextView).text = auth
        if (!TextUtils.isEmpty(imageUrl)) {
            (viewMap[R.id.ios_item_iv] as ImageView).visibility = View.VISIBLE
            Glide.with(mContext).load(imageUrl).placeholder(R.mipmap.preloading).error(R.mipmap.loading_error).into(viewMap[R.id.ios_item_iv] as ImageView)
        } else {
            (viewMap[R.id.ios_item_iv] as ImageView).visibility = View.GONE
        }
    }
}