package javi.toxic.servives

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val lottoBroadcastReceiver: BroadcastReceiver = LottoBroadcastReceiver();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLottoIntentFilter = IntentFilter("jaakko.janka.lottoapp")
        registerReceiver(lottoBroadcastReceiver, myLottoIntentFilter)
    }

    fun startLottoService(view: android.view.View){
        //service must be registered in manifest

        val intent = Intent(this, LottoService::class.java)
        intent.putExtra("AMOUNT_OF_NUMBERS", 7)
        startService(intent)
    }

    inner class LottoBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val lottoNumber = intent?.getIntArrayExtra("LOTTO_NUMBER")

            findViewById<TextView>(R.id.lotto).text = "The lotto numbers: " + lottoNumber[0] + ", " + lottoNumber[1] + ", "+ lottoNumber[2] + ", "+ lottoNumber[3]+ ", " + lottoNumber[4] + ", "+ lottoNumber[5] + ", "+ lottoNumber[6]
        }
    }
}