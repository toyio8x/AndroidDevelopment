package com.example.firstactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityRegister : AppCompatActivity() {

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.register_layout)
        val button:Button = findViewById(R.id.reg_button)
        val myHelper = AccountDatabaseHelper(this,"AccountManagement.db",10)
        val username:EditText = findViewById(R.id.username_reg_input)
        val password:EditText = findViewById(R.id.password_reg_input)
        val confirm:EditText = findViewById(R.id.password_reg_confirm)

        button.setOnClickListener{
            val db = myHelper.writableDatabase
            val name = username.text.toString()
            val pass = password.text.toString()
            val cursor: Cursor= db.rawQuery("select * from AccountManagement where username=?",
                arrayOf(name))
            if(name == ""||pass == ""){
                Toast.makeText(this,"内容不可为空",Toast.LENGTH_SHORT).show()
            }
            else if(cursor.moveToFirst()){
                Toast.makeText(this,"该用户名已存在",Toast.LENGTH_SHORT).show()
                username.setText("")
            }
            else if (confirm.text.toString() != pass){
                Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show()
                password.setText("")
                confirm.setText("")
            }
            else{
                val value = ContentValues().apply {
                    put("username",name)
                    put("password",pass)
                }

                db.insert("AccountManagement",null,value)
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,ActivityLogin::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}








