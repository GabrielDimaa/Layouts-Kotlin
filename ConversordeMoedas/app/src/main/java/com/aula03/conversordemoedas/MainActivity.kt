package com.aula03.conversordemoedas

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.core.graphics.toColor
import androidx.core.view.get
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConvert.setOnClickListener(View.OnClickListener {
            converter(btnConvert)
        })

        val spinner: Spinner = findViewById(R.id.edit_moeda)
        ArrayAdapter.createFromResource(this, R.array.moedas, android.R.layout.simple_spinner_dropdown_item)
            .also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(adapter)
            }

    }

    fun converter(view: View) {
        val moeda = edit_moeda.getSelectedItem().toString()
        val valor = edit_valor.text.toString().toDouble()
        val simbolo = when(moeda) {
            "Dólar" -> "US$"
            "Libra" -> "£"
            "Euro" -> "€"
            "Bitcoin" -> "฿"
            else -> null
        }
        val conversao = when(moeda) {
            "Dólar" -> valor * 5.02
            "Libra" -> valor * 5.82
            "Euro" -> valor * 5.36
            "Bitcoin" -> valor * 31271.47
            else -> null
        }
        edit_simbolo.text = simbolo
        txtResultado.text = "R$ ${conversao.toString()}"
    }

}
