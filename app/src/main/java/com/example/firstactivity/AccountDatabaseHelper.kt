package com.example.firstactivity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AccountDatabaseHelper(private val context: Context, name:String, version:Int): SQLiteOpenHelper(context,name,null,version) {
    private val createAccountManagement = "create table AccountManagement("+"username text primary key,"+"password text)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAccountManagement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion:Int) {
        db.execSQL("drop table AccountManagement")
        onCreate(db)

    }

}