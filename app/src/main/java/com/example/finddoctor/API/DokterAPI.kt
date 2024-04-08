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
    @PUT("finddoctor/dokter/{id}")
    fun updateData(
        @Path("id") id : String,
        @Part("nama_dokter") nama : String,
        @Part("jenis_kelamin") jk : String,
        @Part("alamat") alamat : String
    ) : Call<Dokter>

    @GET("finddoctor/dokter")
    fun getData() : Call<ArrayList<Dokter>>

    @GET("finddoctor/dokter/{id}")
    fun getDataById(@Path("id") id : String) : Call<Dokter>

    @DELETE("finddoctor/dokter/{id}")
    fun deleteData(@Path("id") id : String) : Call<Dokter>
}