package com.example.kotlin.snail.kotlinsnailgank.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.widget.RadioGroup
import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.fragment.AndroidFragment
import com.example.kotlin.snail.kotlinsnailgank.fragment.IosFragment
import com.example.kotlin.snail.kotlinsnailgank.fragment.ResFragment
import com.example.kotlin.snail.kotlinsnailgank.fragment.WelfareFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var fm: FragmentManager? = null
    private var androidFragment: AndroidFragment? = null
    private var iosFragment: IosFragment? = null
    private var welfareFragment: WelfareFragment? = null
    private var resFragment: ResFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        initActionBar()
        initNavigationMenu()
        main_radio_group.setOnCheckedChangeListener(this)
        //默认显示第一个Fragment
        setTabSelection(Constant.ANDROIDFRAGMENT)
    }

    fun initNavigationMenu() {
        main_navigation_view.setCheckedItem(R.id.item_android)
        main_navigation_view.setNavigationItemSelectedListener { item ->
            main_navigation_view.setCheckedItem(item.itemId)
            when (item.itemId) {
                R.id.item_android -> {
                    setTabSelection(Constant.ANDROIDFRAGMENT)
                    android_rb.isChecked = true
                }
                R.id.item_ios -> {
                    setTabSelection(Constant.IOSFRAGMENT)
                    ios_rb.isChecked = true
                }
                R.id.item_welfare -> {
                    setTabSelection(Constant.WELFAREFRAGMENT)
                    welfare_rb.isChecked = true
                }
                R.id.item_res -> {
                    setTabSelection(Constant.RESFRAGMENT)
                    res_rb.isChecked = true
                }
                R.id.item_skin -> {
                    //更换主题的dialog

                }
                R.id.about -> {
                    //跳到关于页面
                }
            }
            closeDrawerLayout()
            false
        }
    }

    /**
     * 关闭左侧 侧滑菜单
     */
    fun closeDrawerLayout() {
        if (mian_drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            mian_drawer_layout.closeDrawers()
        }
    }

    /**
     * 根据逻辑显示不同的Fragment
     *
     * @param index
     */
    fun setTabSelection(index: Int) {
        var transaction = fm?.beginTransaction()
        if (transaction != null) {
            hindeAllFragment(transaction)
        }
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    fun hindeAllFragment(transaction: FragmentTransaction) {
        if (androidFragment != null)
            transaction.hide(androidFragment)

        if (iosFragment != null)
            transaction.hide(iosFragment)

        if (welfareFragment != null)
            transaction.hide(welfareFragment)

        if (resFragment != null)
            transaction.hide(resFragment)

    }

    /**
     * 初始化ActionBar
     */
    fun initActionBar() {
        setSupportActionBar(main_toolbar)
        //设置旋转特效按钮
        val mDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, mian_drawer_layout, main_toolbar, R.string.drawer_open, R.string.drawer_close)
        mDrawerToggle.syncState()
        mian_drawer_layout.addDrawerListener(mDrawerToggle)
        main_toolbar_tv.text = "Android"
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        radioButtonReset()
        when (checkedId) {
            R.id.android_rb -> setTabSelection(Constant.ANDROIDFRAGMENT)
            R.id.ios_rb -> setTabSelection(Constant.IOSFRAGMENT)
            R.id.welfare_rb -> setTabSelection(Constant.WELFAREFRAGMENT)
            R.id.res_rb -> setTabSelection(Constant.RESFRAGMENT)
        }
    }

    /**
     * 所有RadioButton恢复初始值
     */
    fun radioButtonReset() {
        android_rb.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.mipmap.android_up), null, null)
        ios_rb.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.mipmap.ios_up), null, null)
        welfare_rb.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.mipmap.welfare_up), null, null)
        res_rb.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(R.mipmap.res_up), null, null)
    }

}
