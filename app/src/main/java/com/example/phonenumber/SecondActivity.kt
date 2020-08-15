package com.example.phonenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        phonenumber.text = intent.getStringExtra("接受验证码的手机号")
        val number : Array<TextView> = arrayOf(n1,n2,n3,n4,n5,n6) //定义一个数组来存储验证码
        container.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {//start,before,count
            //将验证码中的每个数拆分到方格中
                for ((i:Int,item:Char) in p0?.withIndex()!!){
                    number[i].text = item.toString()
                }
                if (p1 == 5){
                    BmobSMS.verifySmsCode(phonenumber.text.toString(),p0.toString(),object : UpdateListener(){
                        override fun done(e: BmobException?) {
                            if (e == null){
                                Intent(this@SecondActivity,ThirdActivity::class.java).apply {
                                    startActivity(this)
                                }
                            }else{
                                Toast.makeText(this@SecondActivity,"验证失败",Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                }
            }
        })
    }
}
