package javi.toxic.codelab

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.icu.text.AlphabeticIndex
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import android.content.Intent
import android.net.Uri
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var latitude: TextView
    lateinit var longitude: TextView
    var userLat: Double = 0.0
    var userLong: Double = 0.0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        latitude = findViewById<TextView>(R.id.latituide)
        longitude = findViewById<TextView>(R.id.longitude)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        var manager = getSystemService(LOCATION_SERVICE) as LocationManager
        var listener = object: LocationListener{
            override fun onLocationChanged(location: Location) {
                latitude.text = "Latitude: " + location.latitude.toString()
                longitude.text = "Longitude: " + location.longitude.toString()

                userLat = location.latitude
                userLong = location.longitude
            }
        }


        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
        locationUpdate()

    }

    fun locationUpdate(){
        val location = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) //Make sure permissions are good
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }

        location.addOnSuccessListener {
            if(it != null){
                latitude.text = "Latitude: " + it.latitude.toString()
                longitude.text = "Longitude: " + it.longitude.toString()

                userLat = it.latitude
                userLong = it.longitude

            }
        }
    }

    fun showMap(view: android.view.View){
        val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", userLat, userLong)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}
