package com.example.kotlin.snail.kotlinsnailgank.activity

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.base.BaseActivity
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setSupportActionBar(wv_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initData()
        wv_toolbar.setNavigationOnClickListener {
            if (progress_webview.canGoBack()) {
                progress_webview.goBack()
            } else {
                finish()
            }
        }
    }

    private fun initData() {
        val intent = intent
        val url = intent.getStringExtra(Constant.OPENURL)
        if (!TextUtils.isEmpty(url)) {
            progress_webview.loadUrl(url)
            val title = progress_webview.title
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity_webview.removeView(progress_webview)
        progress_webview.destroy()
    }

    override fun onPause() {
        super.onPause()
        progress_webview.reload()
    }
}
