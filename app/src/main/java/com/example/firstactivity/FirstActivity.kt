package com.example.firstactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
//      https://blog.csdn.net/guolin_blog/article/details/113089706
        val button1: Button =findViewById(R.id.button1)
        val usernameText:EditText=findViewById(R.id.username_input)
        val passwordText:EditText=findViewById(R.id.password_input)
        val regButton:TextView=findViewById(R.id.goto_register)
//        val loginBg:ImageView=findViewById(R.id.LoginBg)
//        val progressBar:ProgressBar=findViewById(R.id.processLogin)

        button1.setOnClickListener{
            val inputUsername=usernameText.text.toString()
            Toast.makeText(this,inputUsername+"暂时不可用！",Toast.LENGTH_SHORT).show()
//            loginBg.setImageResource(R.drawable.bg_register)
//            progressBar.visibility= View.GONE
            AlertDialog.Builder(this).apply {
                setTitle("提示")
                setMessage("还没有开放登录")
                setCancelable(false)
                setNeutralButton("确定"){dialog,which->}

                show()
            }
        //            finish()
//            val intent= Intent(this,SecondActivity::class.java)
//            val intent=Intent("com.example.firstactivity.ACTION_START")
//            intent.addCategory("com.example.firstactivity.MY_CATEGORY")
//            startActivity(intent)

        }
        regButton.setOnClickListener{
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item->Toast.makeText(this,"按下了个按钮",Toast.LENGTH_SHORT).show()
            R.id.remove_item->Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show()

        }
       return true
    }
}