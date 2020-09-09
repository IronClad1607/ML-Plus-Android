package com.ironclad.mlplusandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val chestPain = arrayOf(
        "Typical Angina",
        "Atypical Angina",
        "Non - Anginal Pain",
        "Asymptomatic"
    )

    val electroCardiographic = arrayOf(
        "Normal",
        "ST-T wave Abnormality",
        "Left Ventricular Hypertrophy"
    )

    val exerciseAngina = arrayOf(
        "Yes",
        "No"
    )
    var stSegment = arrayOf(
        "Up Sloping",
        "Flat",
        "Down Sloping"
    )
    var thalassemia = arrayOf(
        "Normal",
        "Fixed Defect",
        "Reversible Defect"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}