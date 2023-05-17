package com.thenexprojects.coinapplication102.domain.use_case

import android.util.Log
import androidx.lifecycle.liveData
import com.thenexprojects.coinapplication102.common.Resource
import com.thenexprojects.coinapplication102.common.exception.GeneralRequestException
import com.thenexprojects.coinapplication102.common.exception.UnauthorizedRequestException
import com.thenexprojects.coinapplication102.domain.repository.CoinsRepository
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(private val repo:CoinsRepository){


    operator fun invoke(id:String) =liveData(IO) {
        emit(Resource.Loading)
        try {
            val response = repo.getCoinById(id)
            if (response.isSuccessful && response.code() == 200) {
                emit(Resource.Success(response.body()!!))
                return@liveData
            }
            if (response.code() == 401) {
                emit(Resource.Error(UnauthorizedRequestException("Illegal request")))
                return@liveData
            }
        }catch (e: Exception){
            emit(Resource.Error(e))
            return@liveData
        }
        emit(Resource.Error(GeneralRequestException()))
    }
}