package com.example.tippingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var tv_tip : TextView
    private lateinit var num_billamount: EditText
    private lateinit var sb_tip : SeekBar
    private lateinit var num_total : TextView
    private lateinit var num_tip : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_tip = findViewById(R.id.tv_tip)
        num_billamount = findViewById(R.id.num_billamount)
        sb_tip = findViewById(R.id.sb_tip)
        num_total = findViewById(R.id.editTextNumberDecimal3)
        num_tip = findViewById(R.id.editTextNumberDecimal2)

        sb_tip.progress = 20 // Sets the initial tip
        tv_tip.text = "20%" // Sets the initial tip
        sb_tip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tv_tip.text = "$p1%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        num_billamount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {
        if (num_billamount.text.isEmpty()) {
            num_tip.text = ""
            num_total.text = ""
            return
        }
        val baseAmount = num_billamount.text.toString().toDouble()
        val tipPercent = sb_tip.progress

        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount

        num_tip.text = "%.2f".format(tipAmount)
        num_total.text = "%.2f".format(totalAmount)
    }
}