package javi.toxic.a51weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    val API: String = "9924be9724fd70a935b3f521b109bbe6"
    lateinit var cityLocation: TextView
    lateinit var location: String
    lateinit var new: EditText

    //val url = "https://api.openweathermap.org/data/2.5/weather?q=$location&units=metric&APPID=$API"
    //https://api.openweathermap.org/data/2.5/weather?q=${location}&units=${unit}&APPID=$API"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
        cityLocation = findViewById(R.id.cityLocation)
        new = findViewById(R.id.newLocation)
    }

    fun getWeatherData(view: android.view.View){
        location = new.text.toString()
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${location}&units=metric&APPID=$API"
        val stringRequest = StringRequest( Request.Method.GET, url,
            { response -> handleResponse(response) },
            { handleVolleyError() })

        queue.add( stringRequest )
    }

    private fun handleVolleyError(){
        Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
    }

    private fun handleResponse(response: String){
        //Toast.makeText(this, response, Toast.LENGTH_LONG).show()

        val weatherObject = JSONObject(response)
        val description = weatherObject.getJSONArray("weather").getJSONObject(0).getString("description");
        val temp = weatherObject.getJSONObject("main").getDouble("temp");
        val humidity = weatherObject.getJSONObject("main").getDouble("humidity");
        val windSpeed = weatherObject.getJSONObject("wind").getDouble("speed")

        findViewById<TextView>(R.id.status).text = description
        findViewById<TextView>(R.id.temp).text = "$temp°C"
        findViewById<TextView>(R.id.humidity).text = "$humidity%"
        findViewById<TextView>(R.id.wind).text = windSpeed.toString() + "m/s"
        findViewById<TextView>(R.id.cityLocation).text = location


    }
}

