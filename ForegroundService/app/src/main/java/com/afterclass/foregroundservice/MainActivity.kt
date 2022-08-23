package com.afterclass.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var etInput:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etInput = findViewById(R.id.etInput)
    }
    fun startService(view: View){
          val input = etInput?.text.toString()
        val serviceIntent = Intent(this,ExampleService::class.java)
        serviceIntent.putExtra("inputExtra",input)
        startService(serviceIntent)

    }
    fun stopService(view:View){
        val serviceIntent = Intent(this,ExampleService::class.java)
        stopService(serviceIntent)


    }
}