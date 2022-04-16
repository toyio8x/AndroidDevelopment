package com.example.firstactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.second_layout)
        val button2:Button=findViewById(R.id.reg_button)
        button2.setOnClickListener{
            Toast.makeText(this,"暂未开放注册！",Toast.LENGTH_SHORT).show()
//            val intent=Intent(Intent.ACTION_VIEW)
//            intent.data= Uri.parse("https://www.baidu.com")
//            startActivity(intent)
        }
    }
}