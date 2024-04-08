package com.example.finddoctor.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.finddoctor.API.DokterAPI
import com.example.finddoctor.Config.Koneksi
import com.example.finddoctor.Model.Dokter
import com.example.finddoctor.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahDokter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_dokter)
        init()
    }
    private fun init() : Unit
    {
        txtNama = findViewById(R.id.tvTambahNama)
        txtAlamat = findViewById(R.id.tvTambahAlamat)
        radioPria = findViewById(R.id.radioTambahPria)
        radioWanita = findViewById(R.id.radioTambahWanita)
        buttonProses = findViewById(R.id.button)
        //event
        buttonProses.setOnClickListener({
            buttonProsessOnClickListener(it)
        })
    }
    private fun clearForm()
    {
        txtNama.setText("")
        txtAlamat.setText("")
    }
    private fun buttonProsessOnClickListener(it: View?) {
        var nama : String = txtNama.text.toString()
        var alamat : String = txtAlamat.text.toString()
        var pria : Boolean = radioPria.isChecked
        var wanita : Boolean = radioWanita.isChecked
        var jk : String = ""
        if (nama.trim().equals("")){
            txtNama.setError("Nama Harus diisi")
        } else if(alamat.trim().equals("")){
            txtAlamat.setError("Alamat Harus Diisi")
        } else {
            //post
            if(pria){
                jk = "pria"
            } else if(wanita) {
                jk = "wanita"
            }else {
                jk = "unknown"
            }
            var api : DokterAPI = Koneksi.getKoneksi()!!.create(DokterAPI::class.java)
            var data : Call<Dokter> = api.insertData(nama, jk, alamat)
            data.enqueue(object: Callback<Dokter>{
                override fun onResponse(p0: Call<Dokter>, p1: Response<Dokter>) {
                    with(p1){
                        if(isSuccessful){
                            Toast.makeText(applicationContext, message(), Toast.LENGTH_LONG).show()
                            clearForm()
                            finish()
                        } else if(code() != 200){
                            Toast.makeText(applicationContext, message(), Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(p0: Call<Dokter>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private lateinit var txtNama : TextView
    private lateinit var txtAlamat : TextView
    private lateinit var radioPria : RadioButton
    private lateinit var radioWanita : RadioButton
    private lateinit var buttonProses : Button
}