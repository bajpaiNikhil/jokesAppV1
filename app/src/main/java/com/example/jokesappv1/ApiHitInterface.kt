package com.example.jokesappv1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://api.chucknorris.io/jokes/categories

interface ApiHitInterface {

    @GET("categories")
    fun getJoke():Call<JokesCategory>


    companion object{
        private const val BASE_URL ="https://api.chucknorris.io/jokes/"

        fun getJokeCategory(): ApiHitInterface{

            val builder = Retrofit.Builder()
            builder.baseUrl(BASE_URL)
            builder.addConverterFactory(GsonConverterFactory.create())

            val retrofitObj = builder.build()
            return retrofitObj.create(ApiHitInterface::class.java)

        }

    }

}