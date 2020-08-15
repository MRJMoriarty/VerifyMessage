package com.example.phonenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1.setOnClickListener{
            Intent(this,SecondActivity::class.java).apply {
                putExtra("接受验证码的手机号",t2.text.toString())
                BmobSMS.requestSMSCode(t2.text.toString(),"",object : QueryListener<Int>(){
                    override fun done(p0: Int?, p1: BmobException?) {
                        if (p1 == null) {
                            Toast.makeText(this@MainActivity,"验证码发送成功",Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@MainActivity,"验证码发送失败",Toast.LENGTH_LONG).show()
                        }
                    }
                })
                startActivity(this)
            }
        }
    }
}
