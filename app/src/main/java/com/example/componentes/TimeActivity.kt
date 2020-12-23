package com.example.componentes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*


class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
TimePicker.OnTimeChangedListener {

    private val mSimpleDate = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        buttonDate.setOnClickListener(this)
        buttonGetTime.setOnClickListener(this)
        buttonSetTime.setOnClickListener(this)

        timepicker.setOnTimeChangedListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonDate -> {

                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                DatePickerDialog(this, this, year, month, day).show()
            }
            R.id.buttonGetTime -> {
                /**
                 * Tratamento para versão do android acima da API  e menor
                 */
                if (Build.VERSION.SDK_INT >=23) {
                    val hour = timepicker.hour
                    val minute = timepicker.minute

                    toast("$hour: $minute")
                }
                else {
                    val hour = timepicker.currentHour
                    val minute = timepicker.currentMinute

                    toast("$hour: $minute")
                }
            }
            R.id.buttonSetTime -> {
                if (Build.VERSION.SDK_INT >=23) {
                    timepicker.hour = 20
                    timepicker.minute = 20

                }
                else {
                    timepicker.currentHour = 20
                    timepicker.currentMinute = 20

                }
            }
        }
    }

    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(year, month, dayOfMonth)

        buttonDate.text = mSimpleDate.format(date.time)
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay: $minute")
    }


}