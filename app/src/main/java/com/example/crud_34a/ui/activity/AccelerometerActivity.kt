package com.example.crud_34a.ui.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_34a.R
import com.example.crud_34a.databinding.ActivityAccelerometerBinding

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {
    lateinit var accelerometerBinding: ActivityAccelerometerBinding
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        accelerometerBinding = ActivityAccelerometerBinding.inflate(layoutInflater)
        setContentView(accelerometerBinding.root)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(!checkSensor()){
            return
        }else{
           sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
           sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun checkSensor() : Boolean{
        var sensor = true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            sensor = false
            return sensor
        }
        return sensor
    }

    override fun onSensorChanged(event: SensorEvent?) {
      var values = event!!.values
        var xAxis = values[0]
        var yAxis = values[1]
        var zAxis = values[2]

        accelerometerBinding.lblData.text =
            "X-Axis: $xAxis y-Axis: $yAxis z-Axis: $zAxis"

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}