package com.aula03.frasesdecoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnFrases.setOnClickListener(View.OnClickListener {
            exibirFrase(btnFrases)
        })
    }

    fun exibirFrase(view: View) {
        val frases = resources.getTextArray(R.array.frases)
        var sortear = Random.nextInt(0, frases.size - 1)
        Log.i("Sorteio: ", "Número sorteado $sortear")
        txtFrase.text = frases[sortear]
        Log.i("Sorteio: ", "Frase: $frases")
    }

}
