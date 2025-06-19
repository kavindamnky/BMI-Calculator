package com.example.bmi_calc

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var calculateBtn: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        weightInput = findViewById(R.id.weightInput)
        heightInput = findViewById(R.id.heightInput)
        calculateBtn = findViewById(R.id.calculateBtn)
        resultText = findViewById(R.id.resultText)

        // Set button click listener
        calculateBtn.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weightStr = weightInput.text.toString()
        val heightStr = heightInput.text.toString()

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toFloat()
        val heightCm = heightStr.toFloat()
        val heightM = heightCm / 100

        val bmi = weight / (heightM * heightM)
        val category = getBMICategory(bmi)

        resultText.text = "Your BMI is %.1f\nCategory: %s".format(bmi, category)
    }

    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }
    }
}
