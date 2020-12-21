package com.example.componentes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonToast.setOnClickListener(this)
        buttonSnack.setOnClickListener(this)
        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)
        button_get_seekbar.setOnClickListener(this)
        button_set_seekbar.setOnClickListener(this)


        spinner_static.onItemSelectedListener = this
        seekbar.setOnSeekBarChangeListener(this)

        switchOnOff.setOnCheckedChangeListener(this)


        loadSpinner()
    }

    private fun loadSpinner() {
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
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
            R.id.buttonSnack -> {
                val snack = Snackbar.make(linear_root, "Snack", Snackbar.LENGTH_SHORT)
                snack.setAction("Desfazer", View.OnClickListener {
                    toast("desfeito!")
                })

                snack.setActionTextColor(Color.BLUE)
                snack.setBackgroundTint(Color.GRAY)

                snack.show()
            }
            R.id.button_get_spinner -> {
                val selectedItem = spinner_static.selectedItem

                val selectedItemId = spinner_static.selectedItemId
                val selectedItemPosition = spinner_static.selectedItemPosition

                toast("Position:  $selectedItemId: $selectedItem ")

            }
            R.id.button_set_spinner -> {
                spinner_static.setSelection(3)

            }
            R.id.button_set_seekbar -> {
                seekbar.progress = 15
            }
            R.id.button_get_seekbar -> {
                toast("Seekbar: ${seekbar.progress}")

            }
        }
    }


    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_static -> {
                val texto = parent?.getItemAtPosition(position)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        toast("nothing")
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        text_seekbar_value.text = "Valor seekbar: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        toast("Track stopped")
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.switchOnOff -> {
                toast("Switch: ${if (isChecked) "true" else "false"}")
//                switchOnOff.isChecked = true
            }
        }
    }

}