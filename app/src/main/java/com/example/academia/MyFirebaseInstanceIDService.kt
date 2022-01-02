package com.example.academia


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName ="com.example.academia"

class MyFirebaseInstanceIDService:FirebaseMessagingService() {

    /// generate notificatiom
    ///atach it
    //show it
    /// receive the notification

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification!= null){
            generatingmessage(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)

        }
    }


    fun getRemoveView(title: String, message: String): RemoteViews? {

        var remoteViews = RemoteViews("com.example.academia", R.layout.notification)

        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message,message)
        remoteViews.setImageViewResource(R.id.app_logpo, R.drawable.ic_icon)
        return remoteViews

    }

    fun generatingmessage(title: String, message:String){

        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        //channel name , channel id

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_icon)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoveView(title, message))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }
        notificationManager.notify(0, builder.build())



    }


}