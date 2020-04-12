package com.Ap1.comparadordecerveja

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val lista = mutableListOf<Cerveja>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marca: Spinner = findViewById(R.id.spinner_marca)
        val volume: Spinner = findViewById(R.id.spinner_volume)

        ArrayAdapter.createFromResource(
            this, R.array.marca, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            marca.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this, R.array.ml, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            volume.adapter = adapter
        }


        btnComparar.setOnClickListener(View.OnClickListener {
            val volume = spinner_volume.getSelectedItem().toString().toDouble()
            val marca = spinner_marca.getSelectedItem().toString()
            calcular(volume, marca, volume)
        })

        btnLimpar.setOnClickListener(View.OnClickListener {
            excluir(btnLimpar)
        })

    }

    fun calcular(volume: Double, marca: String, volume1: Double) {
        if(Valor.text.toString().length == 0) {
            Toast.makeText(applicationContext, "Digite um Valor", Toast.LENGTH_SHORT).show()
        } else {
            val valor = Valor.text.toString().toDouble()
            val resultado = volume / valor

            val cerveja = Cerveja(marca, volume, resultado)
            selecionar(cerveja)
        }
    }

    fun excluir(view: View) {
        this.lista.clear()
        ViewResultado.setText("")
    }

    fun selecionar(cerveja: Cerveja) {
        this.lista.add(cerveja)
        val aux = mutableListOf<Double>()
        val aux3 = mutableListOf<Cerveja>()
        val aux2 = this.lista

        for((index, item) in this.lista.withIndex()) {
            aux.add(index, item.preco)
        }
        aux.sort()
        aux.reverse()
        for((index, itemAux) in aux.withIndex()) {
            for((x, itemLista) in aux2.withIndex()) {
                if(itemLista.preco === itemAux) {
                    aux3.add(index, itemLista)
                }
            }
        }
        exibir(aux3)
        Valor.text.clear()
    }

    fun exibir(aux3: MutableList<Cerveja>) {
        ViewResultado.setText("")
        for((index, item) in aux3.withIndex()) {
            ViewResultado.append("\n ${index + 1}º.  ${item.marca} - ${item.volume}ml \n")
        }
    }
}