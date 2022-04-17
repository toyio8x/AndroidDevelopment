package com.example.firstactivity

class News(val title:String, val desc:String, val publishTime:String, val imageUrl:String,val publishAccount:String) {
    override fun toString(): String {
        return "News(title='$title', desc='$desc', publishTime='$publishTime', imageUrl='$imageUrl', publishAccount='$publishAccount')"
    }
}