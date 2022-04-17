package com.example.firstactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ActivityRegister : AppCompatActivity() {

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.register_layout)
        val button: Button = findViewById(R.id.reg_button)
        val myHelper = AccountDatabaseHelper(this, "AccountManagement.db", 10)
        val username: EditText = findViewById(R.id.username_reg_input)
        val password: EditText = findViewById(R.id.password_reg_input)
        val confirm: EditText = findViewById(R.id.password_reg_confirm)

        button.setOnClickListener {
            val db = myHelper.writableDatabase
            val name = username.text.toString()
            val pass = password.text.toString()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://49.235.134.191:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(WebService::class.java)
            if (name == "" || pass == "") {
                Toast.makeText(this, "内容不可为空", Toast.LENGTH_SHORT).show()
            } else if (confirm.text.toString() != pass) {
                Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show()
                password.setText("")
                confirm.setText("")
            } else {
                appService.gotoRegister(name, pass).enqueue(object : Callback<HttpResult<Boolean>> {
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
                                val intent1 = Intent(this@ActivityRegister,ActivityLogin::class.java)
                                startActivity(intent1)
                                finish()
                            }
                            else{
                                Toast.makeText(this@ActivityRegister,"错误"+list.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }


                    override fun onFailure(call: Call<HttpResult<Boolean>>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }
    }
}


//            val cursor: Cursor= db.rawQuery("select * from AccountManagement where username=?",
//                arrayOf(name))
//            if(name == ""||pass == ""){
//                Toast.makeText(this,"内容不可为空",Toast.LENGTH_SHORT).show()
//            }
//            else if(cursor.moveToFirst()){
//                Toast.makeText(this,"该用户名已存在",Toast.LENGTH_SHORT).show()
//                username.setText("")
//            }
//            else if (confirm.text.toString() != pass){
//                Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show()
//                password.setText("")
//                confirm.setText("")
//            }
//            else{
//                val value = ContentValues().apply {
//                    put("username",name)
//                    put("password",pass)
//                }
//
//                db.insert("AccountManagement",null,value)
//                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
//                val intent = Intent(this,ActivityLogin::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//    }










