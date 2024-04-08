package com.example.finddoctor.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class UpdateDokter : AppCompatActivity() {
    lateinit var id : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_dokter)
        init()
        getDataFromServer()
    }

    private fun getDataFromServer() {
        id = intent.getStringExtra("id_dokter").toString()
        var dokter_api : DokterAPI = Koneksi.getKoneksi().create(DokterAPI::class.java)
        var data : Call<Dokter> = dokter_api.getDataById(id)
        data.enqueue(object: Callback<Dokter> {
            override fun onResponse(p0: Call<Dokter>, p1: Response<Dokter>) {
                when(p1.code()){
                    200 -> {
                        with(p1.body()!!) {
                            txtNama.setText(nama_dokter)
                            txtAlamat.setText(alamat)
                            if(jenis_kelamin == "pria"){
                                radioPria.isChecked = true
                            } else if (jenis_kelamin == "wanita"){
                                radioWanita.isChecked = true
                            } else {

                            }
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<Dokter>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun init() : Unit
    {
        txtNama = findViewById(R.id.tvUbahNama)
        txtAlamat = findViewById(R.id.tvUbahAlamat)
        radioPria = findViewById(R.id.radioUbahPria)
        radioWanita = findViewById(R.id.radioUbahWanita)
        buttonProses = findViewById(R.id.buttonUbah)
        buttonHapus = findViewById(R.id.buttonHapus)
        //event
        buttonProses.setOnClickListener({
            buttonProsessOnClickListener(it)
        })
        buttonHapus.setOnClickListener {
            buttonhapusClickListener(it)
        }
    }

    private fun buttonhapusClickListener(it: View?) {
        var dokterAPI : DokterAPI = Koneksi.getKoneksi().create(DokterAPI::class.java)
        var data : Call<Dokter> = dokterAPI.deleteData(id)
        data.enqueue(object: Callback<Dokter>{
            override fun onResponse(p0: Call<Dokter>, p1: Response<Dokter>) {
                Toast.makeText(applicationContext, "Berhasil menghapus data", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(p0: Call<Dokter>, p1: Throwable) {

            }

        })
    }

    private fun buttonProsessOnClickListener(it: View?) {
        var nama : String = txtNama.text.toString()
        var alamat : String =  txtAlamat.text.toString()
        var jk : String = ""
        Log.d("Nama", id)

        if(radioPria.isChecked){
            jk = "pria"
        } else if(radioPria.isChecked){
            jk = "wanita"
        } else {
            jk = ""
        }
        if(nama.trim() == ""){
            txtNama.setError("Nama anda masih kosong")
        } else if(alamat.trim() == ""){
            txtAlamat.setError("alamat anda masih kosong")
        } else {
            var dokterApi : DokterAPI = Koneksi.getKoneksi()!!.create(DokterAPI::class.java)
            var data : Call<Dokter> = dokterApi.updateData(id, "roa", jk, alamat)
            data.enqueue(object : Callback<Dokter>{
                override fun onResponse(p0: Call<Dokter>, p1: Response<Dokter>) {
                    when(p1.code()){
                        200 -> {
                            Toast.makeText(applicationContext, "Berhasil Mengubah Data ", Toast.LENGTH_LONG).show()
                        } else -> {
                            Toast.makeText(applicationContext,  p1.message(), Toast.LENGTH_LONG).show()
                        }
                    }
                    finish()
                }

                override fun onFailure(p0: Call<Dokter>, p1: Throwable) {

                }

            })

        }
    }

    private lateinit var txtNama : TextView
    private lateinit var txtAlamat : TextView
    private lateinit var radioPria : RadioButton
    private lateinit var radioWanita : RadioButton
    private lateinit var buttonProses : Button
    private lateinit var buttonHapus : Button
}