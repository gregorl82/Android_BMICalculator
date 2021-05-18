package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.calculateButton.setOnClickListener {
            val heightInput =
                binding.heightInCm.text.toString().trim()
            val weightInput =
                binding.weightInKg.text.toString().trim()

            if (heightInput.isNotEmpty() && weightInput.isNotEmpty()) {
                val bmi = calculateBMI(
                    heightInCm = heightInput.toDouble(),
                    weightInKg = weightInput.toDouble()
                )
                val bmiStatus = determineStatus(bmi)
                val output = "${String.format("%.1f", bmi)}\n${bmiStatus}"
                binding.tvResultDisplay.text = output

                clearFieldsFocus()
                hideKeyboard(it)

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

    private fun determineStatus(bmi: Double): String {
        return when (bmi) {
            in 0.0..18.49 -> "Underweight"
            in 18.5..24.99 -> "Healthy"
            in 25.0..29.99 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun clearFieldsFocus() {
        binding.heightInputWrapper.clearFocus()
        binding.weightInputWrapper.clearFocus()
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}