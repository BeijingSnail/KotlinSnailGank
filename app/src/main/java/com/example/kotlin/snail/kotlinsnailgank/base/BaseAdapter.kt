package com.example.kotlin.snail.kotlinsnailgank.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*


/**
 * Created by 张志强 on 2017/7/29.
 */
//
abstract class BaseAdapter<T> constructor(open var dataSet: ArrayList<T>?, open val mContext: Context) : RecyclerView.Adapter<ViewHolder>() {

    var mRecyclerViewItemClickListener: RecyclerViewItemClickListener? = null

    open val layoutInflater = LayoutInflater.from(mContext)!!

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        var view = getViewResource(viewType)
        var holder = ViewHolder(view)
        addAllViewItems(holder.viewMap, view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        bindData(holder?.viewMap, dataSet?.get(position), position)
    }


    fun setRecyclerViewItemClickListener(recyclerViewItemClickListener: RecyclerViewItemClickListener) {
        this.mRecyclerViewItemClickListener = recyclerViewItemClickListener
    }

    interface RecyclerViewItemClickListener {
        fun onItemclick(view: View, position: Int)
    }

    /**
     * 记录下item布局下所有控件
     */
    abstract fun addAllViewItems(viewMap: SparseArray<View>, view: View)

    /**
     * 添加item布局
     */
    abstract fun getViewResource(viewType: Int): View

    /**
     * 绑定item数据
     */
    abstract fun bindData(viewMap: SparseArray<View>?, t: T?, position: Int)


    override fun getItemCount(): Int = dataSet?.size ?: 0

    fun addData(data: List<T>) = dataSet?.addAll(data)

    fun removewAll() = dataSet!!.clear()

    fun setDataSet(data: List<T>) {
        dataSet = data as ArrayList<T>
    }

    fun getDataSet(): List<T>? = dataSet

    fun getItem(position: Int): T? = dataSet?.get(position)


}

class ViewHolder : RecyclerView.ViewHolder {
    var viewMap = SparseArray<View>()

    constructor(itemView: View?) : super(itemView)

}
