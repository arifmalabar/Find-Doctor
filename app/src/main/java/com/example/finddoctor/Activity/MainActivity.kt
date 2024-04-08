package com.example.finddoctor.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finddoctor.API.DokterAPI
import com.example.finddoctor.Adapter.DokterAdapter
import com.example.finddoctor.Config.Koneksi
import com.example.finddoctor.Model.Dokter
import com.example.finddoctor.R
import com.example.finddoctor.response.DokterResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init()
    {
        //txtData = findViewById(R.id.testData)
        rv_data = findViewById(R.id.rvData)
        floatBtn = findViewById(R.id.floatingActionButton)
        pb = findViewById(R.id.progressBar)
        //event
        getDataFromRetrofit()
        floatBtn.setOnClickListener({
            floatBtnOnClickListener(it)
        })
    }

    private fun floatBtnOnClickListener(it: View?) {
        var i : Intent = Intent(this, TambahDokter::class.java)
        startActivity(i)
    }

    private fun getDataFromRetrofit() {
        var api : DokterAPI = Koneksi.getKoneksi()!!.create(DokterAPI::class.java)
        var data : Call<ArrayList<Dokter>> = api.getData()
        var nama : String = ""
        pb.visibility = View.VISIBLE
        data.enqueue(object: Callback<ArrayList<Dokter>>{

            override fun onResponse(p0: Call<ArrayList<Dokter>>, p1: Response<ArrayList<Dokter>>) {
                when(p1.code()){
                    200 -> {
                        adapter = DokterAdapter(p1.body()!!, applicationContext)
                        lm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                        rv_data.adapter = adapter
                        rv_data.layoutManager = lm
                        pb.visibility = View.GONE
                    } else -> {

                    }
                }
            }

            override fun onFailure(p0: Call<ArrayList<Dokter>>, p1: Throwable) {
                txtData.setText(p1.message)
            }

        })
    }

    override fun onRestart() {
        super.onRestart()
        getDataFromRetrofit()
    }

    private lateinit var lm : LinearLayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var txtData : TextView
    private lateinit var floatBtn : FloatingActionButton
    private lateinit var rv_data : RecyclerView
    private lateinit var pb : ProgressBar
}