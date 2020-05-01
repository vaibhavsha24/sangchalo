package com.example.sang_chalo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_get_ride.*
import java.text.SimpleDateFormat
import java.util.*

class GetRide : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_ride)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateSetListner = DatePickerDialog.OnDateSetListener{view, year, monthOfYear,dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH,monthOfYear)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat,Locale.US)
            textView4.text = sdf.format(c.time)
        }
        textView4.setOnClickListener{
            DatePickerDialog(this@GetRide, dateSetListner,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show()
        }
        val timepicker=TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
        c.set(Calendar.HOUR_OF_DAY, hour)
        c.set(Calendar.MINUTE, minute)
        timepastid.text = SimpleDateFormat("HH:mm").format(c.time)
    }

        timepastid.setOnClickListener{
            TimePickerDialog(this, timepicker, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show()


        }

        ConfirmAgainButton.setOnClickListener {
            startActivity(Intent(this, AllRides::class.java))

        }


    }
}
