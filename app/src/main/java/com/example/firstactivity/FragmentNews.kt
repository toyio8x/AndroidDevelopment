package com.example.firstactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment


class FragmentNews: ListFragment() {
    private var simpleAdapter: SimpleAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_newslist, container, false)
        simpleAdapter = SimpleAdapter(
            activity,
            getData(),
            R.layout.newslist_item,
            arrayOf("title", "content", "date"),
            intArrayOf(R.id.news_title, R.id.news_content, R.id.news_date)
        )
        this.listAdapter = simpleAdapter
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


}
