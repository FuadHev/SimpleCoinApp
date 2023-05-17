package com.thenexprojects.coinapplication102.data.repository

import com.thenexprojects.coinapplication102.data.api.WebApiService
import com.thenexprojects.coinapplication102.data.model.CoinByIdResponse
import com.thenexprojects.coinapplication102.data.model.CoinsResponse
import com.thenexprojects.coinapplication102.domain.model.Coin
import com.thenexprojects.coinapplication102.domain.repository.CoinsRepository
import retrofit2.Response
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(val api:WebApiService): CoinsRepository {
    override suspend fun getCoins(): Response<List<Coin>> {
        return api.getCoins()
    }

    override suspend fun getCoinById(id: String): Response<CoinByIdResponse> {
        return api.getCoinById(id)
    }

}