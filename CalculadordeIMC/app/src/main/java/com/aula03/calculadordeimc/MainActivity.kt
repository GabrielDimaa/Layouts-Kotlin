package com.aula03.calculadordeimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener(View.OnClickListener {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING

            val resultado: Double = calcular(btnCalcular)
            val conversao = df.format(resultado)

            val intencao = Intent(this,Main2Activity::class.java)
            intencao.putExtra("aqui", conversao.toString())
            startActivity(intencao)
        })
    }

    fun calcular(view: View): Double {
        val altura = id_altura.text.toString().toDouble()
        val peso = id_peso.text.toString().toDouble()
        val resultado = peso / (altura * altura)
        return resultado
    }

}
