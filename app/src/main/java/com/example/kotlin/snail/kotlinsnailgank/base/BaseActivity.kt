package com.example.kotlin.snail.kotlinsnailgank.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View

import com.example.kotlin.snail.kotlinsnailgank.common.ActivityPageManager

open class BaseActivity : AppCompatActivity() {
    var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityPageManager.getInstance().addActivity(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        view = layoutInflater?.inflate(layoutResID, null)
        setContentView(view)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
    }

    fun showSnackBar(strId: Int) {
        Snackbar.make(view!!, strId, Snackbar.LENGTH_SHORT)
    }

    fun showSnackBar(str: String) {
        Snackbar.make(view!!, str, Snackbar.LENGTH_SHORT)
    }


}
