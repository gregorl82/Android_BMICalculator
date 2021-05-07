package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.calculate_button)?.setOnClickListener {
            val heightInput =
                findViewById<TextInputEditText>(R.id.height_in_cm)?.text.toString().trim()
            val weightInput =
                findViewById<TextInputEditText>(R.id.weight_in_kg)?.text.toString().trim()

            if (heightInput.isNotEmpty() && weightInput.isNotEmpty()) {
                val bmi = calculateBMI(
                    heightInCm = heightInput.toDouble(),
                    weightInKg = weightInput.toDouble()
                )
                findViewById<TextView>(R.id.tv_result_display).text = String.format("%.1f", bmi)
            } else {
                Toast.makeText(this, getString(R.string.message), Toast.LENGTH_LONG).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
            }
        }
    }

    private fun calculateBMI(heightInCm: Double, weightInKg: Double): Double {
        val heightInM = heightInCm / 100

        return (weightInKg / (heightInM * heightInM))
    }
}