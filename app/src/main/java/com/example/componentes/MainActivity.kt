package com.example.componentes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonToast.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonToast -> {
                val toast = Toast.makeText(this, "Toast", Toast.LENGTH_SHORT)

                val textView = toast.view?.findViewById<TextView>(android.R.id.message)
                textView?.setTextColor(Color.RED)

                toast.setGravity(Gravity.TOP,0,250)

                val layout = layoutInflater.inflate(R.layout.toast_layout, null)

                layout.findViewById<TextView>(R.id.text_toast)

                toast.view = layout


                toast.show()
            }
        }
    }


    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

    }

}