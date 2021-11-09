package javi.toxic.level

import android.graphics.Color
import android.graphics.Color.blue
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private var levelingStarted: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    fun leveler(view: android.view.View){
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for( sensor in sensorList){
            Toast.makeText(this, sensor.name, Toast.LENGTH_SHORT).show()
        }

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if(accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            levelingStarted = true
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val background = findViewById<ConstraintLayout>(R.id.background)
        val sensorXValue = event?.values?.get(0)
        val sensorYValue = event?.values?.get(1)
        val sensorZValue = event?.values?.get(2)


        if(sensorXValue!! < 5){
            background.setBackgroundColor(Color.BLUE)
        }
        else{
            background.setBackgroundColor(Color.YELLOW)
        }

        if(sensorXValue < 0.1 && sensorXValue > -0.1 && sensorYValue!! < 0.1 && sensorYValue > -0.1){
            Toast.makeText(this, "Phone is Leveled!", Toast.LENGTH_SHORT).show()

        }
        val levelData = findViewById<TextView>(R.id.levelData)
        levelData.text = "X: " + sensorXValue + " \nY: " + sensorYValue

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume(){
        super.onResume()

        if(accelerometer != null && levelingStarted){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}