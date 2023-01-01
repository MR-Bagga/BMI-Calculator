package com.example.bmicalculator

import android.content.Context
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digit = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2digit)
            }
        }
    }
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"height is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            else->{
                return true
            }
        }


    }
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal Range is 5.00-85.00 %"

        var resultText = ""
        var colour = 0
        when {
            bmi<5.00 ->{
                resultText="Underweight"
                colour = R.color.underweight
            }
            bmi in 5.00..84.00->{
                resultText="Healthy"
                colour = R.color.Normal
            }
            bmi in 85.00..94.00->{
                resultText="Overweight"
                colour = R.color.Overweight
            }
            bmi >95.00->{
                resultText = "Obese"
                colour = R.color.Obese
            }
        }

        resultDescription.setTextColor(ContextCompat.getColor(this,colour))
        resultDescription.text = resultText

    }
}