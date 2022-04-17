package com.example.firstactivity

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val button1: Button = findViewById(R.id.button1)
        val usernameText: EditText = findViewById(R.id.username_input)
        val passwordText: EditText = findViewById(R.id.password_input)
        val regButton: TextView = findViewById(R.id.goto_register)
        val clear: TextView = findViewById(R.id.clear_db)
        val myHelper = AccountDatabaseHelper(this, "AccountManagement.db", 10)
        val db = myHelper.writableDatabase
        button1.setOnClickListener {
            val name = usernameText.text.toString()
            val pass = passwordText.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://49.235.134.191:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(WebService::class.java)
            appService.gotoLogin(name, pass).enqueue(object : Callback<HttpResult<Boolean>> {
                override fun onResponse(
                    call: Call<HttpResult<Boolean>>,
                    response: Response<HttpResult<Boolean>>
                ) {
                    val list = response.body()
                    if (list != null) {

                        Log.d("ActivityLogin", "code is ${list.code}")
                        Log.d("ActivityLogin", "message is ${list.message}")
                        Log.d("ActivityLogin", "data is ${list.data}")
                        if (list.code.equals("200")) {
                            val intent1 = Intent(this@ActivityLogin,ActivityMain::class.java)
                            startActivity(intent1)
                            finish()
                        }
                        else{
                            Toast.makeText(this@ActivityLogin,"账号或密码错误",Toast.LENGTH_SHORT).show()
                        }
                    }
                }


                override fun onFailure(call: Call<HttpResult<Boolean>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
//            val cursor: Cursor = db.query("AccountManagement", arrayOf("username","password"),"username=? and password=?", arrayOf(name,pass),null,null,null,null)
//            if(cursor.moveToFirst()){
//                val intent1 = Intent(this,ActivityMain::class.java)
//                startActivity(intent1)
//                finish()
//            }
//            else{
//                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
//                passwordText.setText("")
//                usernameText.setText("")
//            }
        }
        regButton.setOnClickListener {
            val intent2 = Intent(this, ActivityRegister::class.java)
            startActivity(intent2)
        }
        clear.setOnClickListener {
            db.delete("AccountManagement", null, null)
            Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show()
        }
        supportActionBar?.hide()
    }
}
