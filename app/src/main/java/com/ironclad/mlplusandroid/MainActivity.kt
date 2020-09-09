package com.ironclad.mlplusandroid

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.ironclad.mlplusandroid.networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    var chestPainValue = "None"
    var electroCardiographicValue = "None"
    var exerciseAnginaValue = "None"
    var stSegmentValue = "None"
    var thalassemiaValue = "None"

    val vizdata = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chestPainAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                chestPain
            )
        spinnerAngina.adapter = chestPainAdapter

        val ecAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                electroCardiographic
            )
        spinnerCardiographic.adapter = ecAdapter

        val exerciseAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                exerciseAngina
            )
        spinnerExercise.adapter = exerciseAdapter

        val stDepressionAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                stSegment
            )
        spinnerSegment.adapter = stDepressionAdapter

        val thalasseiaAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                thalassemia
            )
        spinnerDefect.adapter = thalasseiaAdapter

        btnPredict.setOnClickListener {
            vizdata.clear()
            val age = etAge.editText?.text.toString()
            val bloodPressure = etBloodPressure.editText?.text.toString()
            val cholesterol = etCholesterol.editText?.text.toString()
            val bloodSugar = etBloodSugar.editText?.text.toString()
            val maxHeartRate = etMaxHeartRate.editText?.text.toString()
            val stDepression = etSTDepression.editText?.text.toString()
            val maxVessel = etMaxVessels.editText?.text.toString()
            chestPainValue = spinnerAngina.selectedItem.toString()
            electroCardiographicValue = spinnerCardiographic.selectedItem.toString()
            exerciseAnginaValue = spinnerExercise.selectedItem.toString()
            stSegmentValue = spinnerSegment.selectedItem.toString()
            thalassemiaValue = spinnerDefect.selectedItem.toString()
            val genderID = radioGroup.checkedRadioButtonId
            val gender = findViewById<RadioButton>(genderID)
            Log.d("PUI", "${gender.text}")

            makeResponseData(
                gender.text.toString(),
                age,
                bloodPressure,
                cholesterol,
                bloodSugar,
                maxHeartRate,
                stDepression,
                maxVessel,
                chestPainValue,
                electroCardiographicValue,
                exerciseAnginaValue,
                stSegmentValue,
                thalassemiaValue
            )

            Log.d("PUI", "$vizdata")

            GlobalScope.launch {
                val data = getResponse(vizdata)
                Log.d("PUI", data!!.isHeart)
                runOnUiThread {
                    tvAnswer.text = data.isHeart
                }
            }
        }

    }

    private suspend fun getResponse(vizdata: ArrayList<String>): Data? {
        Log.d("PUI", "API Call Started!")
        val dataAPI = RetrofitClient.apiCall
        val responseData = dataAPI.getResponse(vizdata)

        return if (responseData.isSuccessful) {
            Log.d("PUI", "API Call Success")
            responseData.body()
        } else {
            Log.d("PUI", "API Call Not Success")
            null
        }
    }


    private fun makeResponseData(
        gender: String,
        age: String,
        bloodPressure: String,
        cholesterol: String,
        bloodSugar: String,
        maxHeartRate: String,
        stDepression: String,
        maxVessel: String,
        chestPainValue: String,
        electroCardiographicValue: String,
        exerciseAnginaValue: String,
        stSegmentValue: String,
        thalassemiaValue: String
    ) {
        vizdata.add(age)
        when (gender) {
            "Male" -> {
                vizdata.add("1")
            }
            "Female" -> {
                vizdata.add("0")
            }
        }

        when (chestPainValue) {
            "Typical Angina" -> {
                vizdata.add("1")
            }
            "Atypical Angina" -> {
                vizdata.add("2")
            }
            "Non - Anginal Pain" -> {
                vizdata.add("3")
            }
            "Asymptomatic" -> {
                vizdata.add("4")
            }
        }

        vizdata.add(bloodPressure)
        vizdata.add(cholesterol)
        vizdata.add(bloodSugar)

        when (electroCardiographicValue) {
            "Normal" -> {
                vizdata.add("1")
            }
            "ST-T wave Abnormality" -> {
                vizdata.add("2")
            }
            "Left Ventricular Hypertrophy" -> {
                vizdata.add("3")
            }
        }
        vizdata.add(maxHeartRate)
        when (exerciseAnginaValue) {
            "Yes" -> {
                vizdata.add("1")
            }
            "No" -> {
                vizdata.add("0")
            }
        }
        vizdata.add(stDepression)
        when (stSegmentValue) {
            "Up Sloping" -> {
                vizdata.add("1")
            }
            "Flat" -> {
                vizdata.add("2")
            }
            "Down Sloping" -> {
                vizdata.add("3")
            }
        }
        vizdata.add(maxVessel)
        when (thalassemiaValue) {
            "Normal" -> {
                vizdata.add("1")
            }
            "Fixed Defect" -> {
                vizdata.add("2")
            }
            "Reversible Defect" -> {
                vizdata.add("3")
            }
        }
    }
}