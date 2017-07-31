package com.example.kotlin.snail.kotlinsnailgank.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlin.snail.kotlinsnailgank.R

/**
 * Created by 张志强 on 2017/7/31.
 */
class PictureAdapter(context: Context, list: List<String>) : PagerAdapter() {
    var urlList: List<String>? = null
    var inflater: LayoutInflater? = null
    var mContext: Context? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.urlList = list
        this.mContext = context
    }


    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return urlList?.size ?: 0
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): View {
        val url = urlList?.get(position)
        val view = inflater?.inflate(R.layout.item_picture_layout, container, false)
        val imageView = view?.findViewById(R.id.item_pic_iv) as ImageView
        Glide.with(mContext).load(url).placeholder(R.mipmap.preloading).error(R.mipmap.loading_error).into(imageView)
        container?.addView(view)
        return view!!
    }
}