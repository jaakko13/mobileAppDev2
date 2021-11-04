package javi.toxic.a11bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateBMI(view: View){
        val weight = findViewById<EditText>(R.id.editWeight).text.toString().toDouble()
        val height = findViewById<EditText>(R.id.editHeight).text.toString().toDouble()
        val bmi = weight / ( height / 100 * height / 100)

        findViewById<TextView>(R.id.result).text = bmi.toString()
    }
}