package com.example.firstactivity

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast



class ActivityStart:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(com.example.firstactivity.R.layout.start_layout)
        val myHelper = AccountDatabaseHelper(this,"AccountManagement.db",10)
        val db = myHelper.writableDatabase
        val cursor: Cursor = db.rawQuery("select * from AccountManagement",null)
        if(cursor.count != 0) {
            val intent1 = Intent(this, ActivityMain::class.java)
            startActivity(intent1)
        }
        else{
            val intent2 = Intent(this,ActivityLogin::class.java)
            startActivity(intent2)
            }
        finish()

    }
}