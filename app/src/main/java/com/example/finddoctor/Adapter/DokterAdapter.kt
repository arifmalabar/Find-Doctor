package com.example.finddoctor.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finddoctor.Activity.UpdateDokter
import com.example.finddoctor.Model.Dokter
import com.example.finddoctor.R

open class DokterAdapter : RecyclerView.Adapter<DokterAdapter.HolderDokter> {
    var data_dokter: List<Dokter> = ArrayList<Dokter>()
    lateinit var ctx : Context
    //lateinit var act : Activity

    constructor(data_dokter: List<Dokter>, ctx: Context) : super() {
        Log.d("Tes", data_dokter.size.toString());
        this.data_dokter = data_dokter
        this.ctx = ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDokter {
        var v : View = LayoutInflater.from(parent.context).inflate(R.layout.list_dokter, parent, false)
        return HolderDokter(v)
    }

    override fun getItemCount(): Int {
        return data_dokter.size
    }

    override fun onBindViewHolder(holder: HolderDokter, position: Int) {
        var d : Dokter = data_dokter.get(position)
        holder.run {
            with(d){
                itemId.setText(id)
                itemNama.setText(nama_dokter)
                itemJk.setText(jenis_kelamin)
                itemAlamat.setText(alamat)
            }
        }
    }
    inner class HolderDokter(itemView: View) : ViewHolder(itemView) {
        lateinit var itemNama : TextView
        lateinit var itemJk : TextView
        lateinit var itemAlamat : TextView
        lateinit var itemId : TextView
        init {
            with(itemView){
                itemId = findViewById(R.id.itemId)
                itemNama = findViewById(R.id.itemNama)
                itemAlamat = findViewById(R.id.itemAlamat)
                itemJk = findViewById(R.id.itemJk)
                setOnClickListener({
                    var i : Intent = Intent(ctx.applicationContext, UpdateDokter::class.java)
                    with(i){
                        putExtra("id_dokter", itemId.text);
                    }
                    ctx.startActivity(i)
                })
            }

        }

    }
}