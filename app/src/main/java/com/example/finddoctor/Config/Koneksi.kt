package com.example.finddoctor.Config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Koneksi {
    fun getKoneksi() : Retrofit{
        return Retrofit.Builder().baseUrl("https://656d2369bcc5618d3c22dc61.mockapi.io/").addConverterFactory(GsonConverterFactory.create()).build()
    }
}