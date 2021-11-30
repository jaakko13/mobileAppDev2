package javi.toxic.servives

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class LottoService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        Thread{
            val lottoNumber = IntArray(7) { Random.nextInt(0,100) }

            val lottoBroadcast = Intent("jaakko.janka.lottoapp")
            lottoBroadcast.putExtra("LOTTO_NUMBER", lottoNumber)
            sendBroadcast(lottoBroadcast)
        }.start()

        return START_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }
}