package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }

    }

    private fun calculateTip() {

        val stringInTextField: String = binding.costOfServiceEditText.text.toString()

        val cost: Double? = stringInTextField.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            //El return nos hace salir a la fuerza de la función
            //Sin ejecutar más código
            return
        }

        val tipPercentage: Double = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip: Double = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        displayTip(tip)

    }

    private fun displayTip(tip: Double) {

        val formattedTip: String = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = resources.getString(R.string.tip_mount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //Ocultar el teclado
            val inputMethodManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}