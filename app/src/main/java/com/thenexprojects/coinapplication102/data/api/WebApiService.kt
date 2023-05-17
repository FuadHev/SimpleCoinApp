package com.thenexprojects.coinapplication102.data.api

import com.thenexprojects.coinapplication102.data.model.CoinByIdResponse
import com.thenexprojects.coinapplication102.data.model.CoinsResponse
import com.thenexprojects.coinapplication102.domain.model.Coin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebApiService {


    @GET("v1/coins")
    suspend fun getCoins():Response<List<Coin>>

    @GET("v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id:String):Response<CoinByIdResponse>

}