package com.example.finddoctor.API

import com.example.finddoctor.Model.Dokter
import com.example.finddoctor.response.DokterResponse
import retrofit2.Call
import retrofit2.http.*

interface DokterAPI {
    @FormUrlEncoded
    @POST("finddoctor/dokter")
    fun insertData(
        @Field("nama_dokter") nama : String,
        @Field("jenis_kelamin") jk : String,
        @Field("alamat") alamat : String
    ) : Call<Dokter>

    @Multipart
    @PUT("finddoctor/dokter")
    fun updateData(
        @Field("id") id : String,
        @Field("nama_dokter") nama : String,
        @Field("jenis_kelamin") jk : String,
        @Field("alamat") alamat : String
    ) : Call<Dokter>

    @GET("finddoctor/dokter")
    fun getData() : Call<ArrayList<Dokter>>
}