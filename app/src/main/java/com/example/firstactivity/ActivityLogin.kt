package com.example.firstactivity

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register_layout.*

class ActivityLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        val button1: Button =findViewById(R.id.button1)
        val usernameText:EditText=findViewById(R.id.username_input)
        val passwordText:EditText=findViewById(R.id.password_input)
        val regButton:TextView=findViewById(R.id.goto_register)
        val clear:TextView = findViewById(R.id.clear_db)
        val myHelper = AccountDatabaseHelper(this,"AccountManagement.db",10)
        val db = myHelper.writableDatabase
        button1.setOnClickListener{
            val name = usernameText.text.toString()
            val pass = passwordText.text.toString()
            val cursor: Cursor = db.query("AccountManagement", arrayOf("username","password"),"username=? and password=?", arrayOf(name,pass),null,null,null,null)
            if(cursor.moveToFirst()){
                val intent1 = Intent(this,ActivityMain::class.java)
                startActivity(intent1)
                finish()
            }
            else{
                Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show()
                passwordText.setText("")
                usernameText.setText("")
            }
        }
        regButton.setOnClickListener{
            val intent2 = Intent(this,ActivityRegister::class.java)
            startActivity(intent2)
        }
        clear.setOnClickListener{
            db.delete("AccountManagement",null,null)
            Toast.makeText(this,"已删除",Toast.LENGTH_SHORT).show()
        }
        supportActionBar?.hide()
    }
}
