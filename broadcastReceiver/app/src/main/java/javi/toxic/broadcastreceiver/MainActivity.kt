package javi.toxic.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val connectorReceiver: BroadcastReceiver = ConnectorReceiver();
    lateinit var powerText: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        powerText = findViewById<TextView>(R.id.chargerStatus)

        //create and register receiver when its not in separate file and manifest
        //intent filter defines broadcast
        val powerConnectedFilter = IntentFilter( Intent.ACTION_POWER_CONNECTED )
        val powerDisconnectedFilter = IntentFilter( Intent.ACTION_POWER_DISCONNECTED )

        //register broadcast with intent filter and broadcast
        registerReceiver(connectorReceiver, powerConnectedFilter)
        registerReceiver(connectorReceiver, powerDisconnectedFilter)

    }

    //nested class. Access to UI elements
    inner class ConnectorReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
            if( intent.action.equals( Intent.ACTION_POWER_CONNECTED)){
                //power is connected
                Toast.makeText(context, "Power is Connected!", Toast.LENGTH_LONG).show()
                powerText.text = "Charger Status: Connected"
            }

            if( intent.action.equals( Intent.ACTION_POWER_DISCONNECTED)){
                //power is disconnected
                Toast.makeText(context, "Power is Disconnected!", Toast.LENGTH_LONG).show()
                powerText.text = "Charger Status: Disconnected"
            }
        }
    }
}