package com.example.firstactivity


import android.content.Intent
import android.widget.LinearLayout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class ActivityMain :AppCompatActivity (),View.OnClickListener{
    //声明四个Tab的布局文件
    private var newsTab: LinearLayout? = null
    private var hometab: LinearLayout? = null
    private var phototab1: LinearLayout? = null
    private var phototab2: LinearLayout? = null


    //声明四个Tab分别对应的Fragment
    private var fragmenthome: Fragment? = null
    private var fragmentphoto2: Fragment? = null
    private var fragmentnewslist: Fragment? = null
    private var fragmentphoto1: Fragment? = null
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        setContentView(R.layout.main_layout)
        initViews() //初始化控件
        initEvents() //初始化事件
        selectTab(0) //默认选中第一个Tab
    }


    private fun initEvents() {
        //初始化四个Tab的点击事件
        newsTab!!.setOnClickListener(this)
        phototab1!!.setOnClickListener(this)
        hometab!!.setOnClickListener(this)
        phototab2!!.setOnClickListener(this)
    }

    private fun initViews() {
        //初始化四个Tab的布局文件
        newsTab = findViewById<View>(R.id.news_tab) as LinearLayout
        hometab = findViewById<View>(R.id.home_tab) as LinearLayout
        phototab1 = findViewById<View>(R.id.photo_tab1) as LinearLayout
        phototab2 = findViewById<View>(R.id.photo_tab2) as LinearLayout



    }

    //处理Tab的点击事件
    override fun onClick(v: View) {
        when (v.id) {
            R.id.news_tab -> selectTab(0)
            R.id.photo_tab1 -> selectTab(1)
            R.id.home_tab -> selectTab(2)
            R.id.photo_tab2 -> selectTab(3)
        }
    }

    //进行选中Tab的处理
    private fun selectTab(i: Int) {
        //获取FragmentManager对象

        //获取FragmentTransaction对象
        val transaction = manager.beginTransaction()
        //先隐藏所有的Fragment
        hideFragments(transaction)
        when (i) {
            0 ->
                if (fragmentnewslist == null) {
                    fragmentnewslist = FragmentNews()
                    transaction.add(R.id.newsList_fragment, fragmentnewslist!!)

                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(fragmentnewslist!!)
                }
            1 ->                                 //mImg2.setImageResource(R.mipmap.icon1);
                if (fragmentphoto1 == null) {
                    fragmentphoto1 = FragmentTakePhotos1()
                    transaction.add(R.id.newsList_fragment, fragmentphoto1!!)
                } else {
                    transaction.show(fragmentphoto1!!)
                }
            2 ->                                 //mImg3.setImageResource(R.mipmap.icon1);
                if (fragmenthome == null) {
                    fragmenthome = FragmentHome()
                    transaction.add(R.id.newsList_fragment, fragmenthome!!)
                } else {
                    transaction.show(fragmenthome!!)
                }
            3 ->                                 //mImg4.setImageResource(R.mipmap.icon1);
                if (fragmentphoto2 == null) {
                    fragmentphoto2 = FragmentTakePhotos2()
                    transaction.add(R.id.newsList_fragment, fragmentphoto2!!)
                } else {
                    transaction.show(fragmentphoto2!!)
                }
        }
        //不要忘记提交事务
        transaction.commit()
    }

    //将四个的Fragment隐藏
    private fun hideFragments(transaction: FragmentTransaction) {
        if (fragmentnewslist != null) {
            transaction.hide(fragmentnewslist!!)
        }
        if (fragmentphoto1 != null) {
            transaction.hide(fragmentphoto1!!)
        }
        if (fragmenthome != null) {
            transaction.hide(fragmenthome!!)
        }
        if (fragmentphoto2 != null) {
            transaction.hide(fragmentphoto2!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.exit_login ->{
//                val intent = Intent(this,ActivityLogin::class.java)
//                startActivity(intent)
//            finish()
//            }

        }
        return true
    }
}