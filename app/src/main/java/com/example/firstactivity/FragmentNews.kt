package com.example.firstactivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread


class FragmentNews: ListFragment() {

    private var simpleAdapter: SimpleAdapter? = null
    private var newsList=ArrayList<News>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_newslist, container, false)
//
//        simpleAdapter = SimpleAdapter(
//            activity,
//            getData(),
//            R.layout.newslist_item,
//            arrayOf("title", "content", "date","image"),
//            intArrayOf(R.id.news_title, R.id.news_content, R.id.news_date,R.id.news_img)
//        )
        val retrofit = Retrofit.Builder()
            .baseUrl("http://49.235.134.191:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val appService = retrofit.create(WebService::class.java)
        appService.getNews().enqueue(object : Callback<HttpResult<List<News>>> {
            override fun onResponse(
                call: Call<HttpResult<List<News>>>,
                response: Response<HttpResult<List<News>>>
            ) {
                val list = response.body()
                if (list != null) {
                    newsList.addAll(list.data)
                    Log.d("FragmentNews", "code is ${list.code}")
                    Log.d("FragmentNews", "message is ${list.message}")
                    Log.d("FragmentNews", "data is ${newsList.toString()}")
                    if (list.code.equals("200")) {
                        Toast.makeText(activity,"刷新成功", Toast.LENGTH_SHORT).show()
                    }
                    val fgAdapter=FragmentNewsAdapter(activity as Activity,R.layout.newslist_item,newsList)
                    this@FragmentNews.listAdapter = fgAdapter
                }
            }


            override fun onFailure(call: Call<HttpResult<List<News>>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return view
    }

    private fun getData(): MutableList<MutableMap<String,Any>> {
        val title = arrayOf("防控疫情，戴好口罩", "食堂门口漏油", "爱护草坪，带走垃圾", "井盖碎裂")
        val content = arrayOf(
            "近日发现部分同学没带口罩进入食堂，疫情尚未结束，请大家进入食堂时戴好口罩",
            "紫荆食堂门口的地面上都是油，大家小心！注意绕行",
            "昨天，有同学在阳光园的草坪上野餐完没有及时清理垃圾，请同学们自觉带走垃圾，共同维护干净整洁的校园",
            "生活三区门口井盖碎裂，过往车辆小心，注意绕行"
        )
        val date = arrayOf(
            "2021-5-24 12:35:20", "2021-5-25 12:35:20", "2021-5-26 12:35:20",
            "2021-5-27 12:35:20"
        )


        val list: MutableList<MutableMap<String, Any>> = ArrayList()
        for (i in 0..3) {
            val map: MutableMap<String, Any> = HashMap()
            map["title"] = title[i]
            map["content"] = content[i]
            map["date"] = date[i]

            list.add(map)
        }

        return list
    }




}
