package com.example.testservice

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MyService : Service() {
    var binder: Binder = MyBinder()
   lateinit var mediaPlayer : MediaPlayer
    lateinit var i : Intent
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Service Creat",Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.tinh_yeu_chap_va)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        i = Intent(this,MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,0,i,0)

        val notification = NotificationCompat.Builder(this,"channel")
                .setContentTitle("Phát nhạc")
                .setContentText("Tình yêu chắp vá")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1,notification)
        mediaPlayer.start()
        Toast.makeText(this,"Service onStartCommand : ${Thread.currentThread().name}",Toast.LENGTH_SHORT).show()
        return START_NOT_STICKY
    }
    @SuppressLint("NewApi")
    override fun onBind(intent: Intent): IBinder? {
        Toast.makeText(this,"Service onBind",Toast.LENGTH_SHORT).show()
        return binder
    }


    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this,"Service onUnbind",Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Toast.makeText(this,"Service onRebind",Toast.LENGTH_SHORT).show()

    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        Toast.makeText(this,"Service onDestroy",Toast.LENGTH_SHORT).show()
    }
    inner class MyBinder : Binder() {
        fun getService() : MyService = this@MyService
    }
    fun printResult(result : String) : String{
        return "Result = "+result
    }
}