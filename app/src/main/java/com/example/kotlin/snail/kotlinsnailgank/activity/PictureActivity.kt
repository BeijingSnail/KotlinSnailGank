package com.example.kotlin.snail.kotlinsnailgank.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.adapter.PictureAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseActivity
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import kotlinx.android.synthetic.main.activity_picture.*
import java.util.*

class PictureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        initToolbar()
        initData()
    }

    private fun initData() {
        var intent = intent
        var position = intent.getIntExtra(Constant.POSITION, 0)
        var urlList: ArrayList<String> = intent.getStringArrayListExtra(Constant.UELLIST)
        if (urlList == null || urlList.isEmpty()) {
            return
        }
        var format = resources.getString(R.string.PictureCount)
        picture_right_up_tv.text = String.format(format, (position + 1).toString() + "", urlList.size.toString() + "")
        picture_vp.adapter = PictureAdapter(this, urlList)
        picture_vp.currentItem = position
        picture_vp.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                picture_right_up_tv.text = String.format(format, (position + 1).toString() + "", urlList.size.toString() + "")
            }
        })
    }

    private fun initToolbar() {
        picture_toolbar.setTitle(R.string.WelfarePicture)
        setSupportActionBar(picture_toolbar)
        picture_toolbar.setNavigationOnClickListener { finish() }
        val bar = supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
    }
}
