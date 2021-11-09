package javi.toxic.flashlight

import android.graphics.Color
import android.hardware.SensorManager
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private var torch = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashlight = findViewById<Button>(R.id.flashlight)
    }

    fun switchFlashlight(view: android.view.View){
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        for( id in cameraManager.cameraIdList){
            if(cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true){
                torch = !torch
                cameraManager.setTorchMode(id, torch)
            }

            val background = findViewById<ConstraintLayout>(R.id.backgroundConstraintLayout)
            if(torch){
                background.setBackgroundColor(Color.YELLOW)
            }
            else{
                background.setBackgroundColor(Color.BLACK)
            }
        }


    }
}