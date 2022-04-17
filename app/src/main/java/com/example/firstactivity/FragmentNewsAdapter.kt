package com.example.firstactivity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FragmentNewsAdapter(activity:Activity,val resourceID:Int,data:List<News>):ArrayAdapter<News>(activity,resourceID,data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceID, parent, false)
        val title:TextView=view.findViewById(R.id.news_title)
        val content:TextView=view.findViewById(R.id.news_content)
        val date:TextView=view.findViewById(R.id.news_date)
        val imageView:ImageView=view.findViewById(R.id.news_img)
        val curItem=getItem(position)
        if(curItem!=null){
            title.text=curItem.title
            content.text=curItem.desc
            date.text=curItem.publishTime
            Glide.with(context).load(curItem.imageUrl).into(imageView)
        }

        return view;
    }
}