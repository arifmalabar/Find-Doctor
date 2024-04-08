package com.example.finddoctor.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.finddoctor.Model.Dokter
import com.example.finddoctor.R

open class DokterAdapter : RecyclerView.Adapter<DokterAdapter.HolderDokter> {
    var data_dokter: List<Dokter> = ArrayList<Dokter>()
    lateinit var ctx : Context

    constructor(data_dokter: List<Dokter>, ctx: Context) : super() {
        this.data_dokter = data_dokter
        this.ctx = ctx
    }


    inner class HolderDokter(itemView: View) : ViewHolder(itemView) {
        lateinit var itemNama : TextView
        lateinit var itemJk : TextView
        lateinit var itemAlamat : TextView
        init {
            itemNama = itemView.findViewById(R.id.itemNama)
            itemAlamat = itemView.findViewById(R.id.itemAlamat)
            itemJk = itemView.findViewById(R.id.itemJk)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderDokter {
        var v : View = LayoutInflater.from(parent.context).inflate(R.layout.list_dokter, parent, false)
        return HolderDokter(v)
    }

    override fun getItemCount(): Int {
        return data_dokter.size
    }

    override fun onBindViewHolder(holder: HolderDokter, position: Int) {
        var d : Dokter = Dokter()
        holder.run {
            with(d){
                itemNama.setText(nama_dokter)
                itemJk.setText(jenis_kelamin)
                itemAlamat.setText(alamat)
            }
        }
    }
}