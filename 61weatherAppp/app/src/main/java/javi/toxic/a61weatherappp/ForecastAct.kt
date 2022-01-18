package javi.toxic.a61weatherappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import android.content.Intent.getIntent

import android.content.Intent



class ForecastAct : AppCompatActivity() {

    //lateinit var queue: RequestQueue
    val API: String = "9924be9724fd70a935b3f521b109bbe6"
    lateinit var location: String
    lateinit var cityLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast2)
        val intent = getIntent()
        location = intent.getStringExtra("location").toString()
        
        cityLocation = findViewById(R.id.cityLocation)

        //queue = Volley.newRequestQueue(this)
        getWeatherData()
        val forecastListTextView = findViewById<TextView>(R.id.forecastListTextView)
    }

    private fun getWeatherData(){
        //location = new.text.toString()  ${location}
        cityLocation.text = location
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${location}&units=metric&APPID=$API"
        val stringRequest = StringRequest( Request.Method.GET, url,
            { response -> handleResponse(response) },
            { handleVolleyError() })

        //queue.add( stringRequest )
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)

    }


    private fun handleVolleyError(){
        Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
    }

    private fun handleResponse(response: String){
        //Toast.makeText(this, response, Toast.LENGTH_LONG).show()
        val rootJsonObject: JSONObject = JSONObject(response);
        val forecastList = rootJsonObject.getJSONArray("list")
        for(i in 0 until forecastList.length()){
            val time: String = forecastList.getJSONObject(i).getString("dt_txt")
            val temperature: Int = forecastList.getJSONObject(i).getJSONObject("main").getDouble("temp").toInt()
            val description: String = forecastList.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main")

            findViewById<TextView>(R.id.forecastListTextView).append(time + " " + description + " " + temperature + "C\n")
        }
    }
}