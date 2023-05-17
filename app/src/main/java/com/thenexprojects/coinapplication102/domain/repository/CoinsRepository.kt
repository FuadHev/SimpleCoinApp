package com.thenexprojects.coinapplication102.domain.repository

import com.thenexprojects.coinapplication102.data.model.CoinByIdResponse
import com.thenexprojects.coinapplication102.data.model.CoinsResponse
import com.thenexprojects.coinapplication102.domain.model.Coin
import retrofit2.Response

interface CoinsRepository {

    suspend fun getCoins(): Response<List<Coin>>


    suspend fun getCoinById(id:String):Response<CoinByIdResponse>

}