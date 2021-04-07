package com.example.testservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var i:Intent
    lateinit var myService : MyService
    lateinit var connection:ServiceConnection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        i = Intent(this,MyService::class.java)
        connection = object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                var binder : MyService.MyBinder = service as MyService.MyBinder
                myService = binder.getService()
            }
            override fun onServiceDisconnected(name: ComponentName?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show()
    }

    fun printResult(v:View) {
//        var result = myService.printResult(findViewById<EditText>(R.id.input).text.toString())
//       Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
    }
    fun stopService(v:View) {
        stopService(i)
//        unbindService(connection)
    }
    fun startService(v:View) {
        startService(i)
//        bindService(i,connection, BIND_AUTO_CREATE)
    }
//
}