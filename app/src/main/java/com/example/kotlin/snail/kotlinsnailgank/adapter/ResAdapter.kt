package com.example.kotlin.snail.kotlinsnailgank.adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.bean.DataBean

/**
 * Created by 张志强 on 2017/7/31.
 */
class ResAdapter<T>(context: Context) : BaseAdapter<T>(context) {
    override fun addAllViewItems(viewMap: SparseArray<View>, view: View) {
        viewMap.put(R.id.res_desc_tv, view.findViewById(R.id.res_desc_tv))
        viewMap.put(R.id.res_auth_tv, view.findViewById(R.id.res_auth_tv))
        viewMap.put(R.id.res_time_tv, view.findViewById(R.id.res_time_tv))
    }

    override fun getViewResource(viewType: Int): View {
        return layoutInflater.inflate(R.layout.item_res_layout, null)
    }

    override fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int) {
        val resBean: DataBean = t as DataBean
        (viewMap!![R.id.res_desc_tv] as TextView).text = resBean.desc
        (viewMap!![R.id.res_auth_tv] as TextView).text = resBean.who
        (viewMap!![R.id.res_time_tv] as TextView).text = resBean.createdAt
    }
}