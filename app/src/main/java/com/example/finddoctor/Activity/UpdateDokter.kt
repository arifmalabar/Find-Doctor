package com.example.finddoctor.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.finddoctor.R

class UpdateDokter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dokter)
    }
    fun init() : Unit
    {
        txtNama = findViewById(R.id.tvUbahNama)
        txtAlamat = findViewById(R.id.tvUbahAlamat)
        radioPria = findViewById(R.id.radioUbahPria)
        radioWanita = findViewById(R.id.radioUbahWanita)
        buttonProses = findViewById(R.id.buttonUbah)
        //event
        buttonProses.setOnClickListener({
            buttonProsessOnClickListener(it)
        })
    }

    private fun buttonProsessOnClickListener(it: View?) {

    }

    private lateinit var txtNama : TextView
    private lateinit var txtAlamat : TextView
    private lateinit var radioPria : RadioButton
    private lateinit var radioWanita : RadioButton
    private lateinit var buttonProses : Button
}