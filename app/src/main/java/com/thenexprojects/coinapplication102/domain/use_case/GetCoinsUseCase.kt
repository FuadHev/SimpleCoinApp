package com.thenexprojects.coinapplication102.domain.use_case

import android.util.Log
import androidx.lifecycle.liveData
import com.thenexprojects.coinapplication102.common.Resource
import com.thenexprojects.coinapplication102.common.exception.GeneralRequestException
import com.thenexprojects.coinapplication102.common.exception.UnauthorizedRequestException
import com.thenexprojects.coinapplication102.domain.repository.CoinsRepository
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(var repository: CoinsRepository) {


    //kohne yolla yazsaq bele olacaq

    /*    private val _coinsLiveData = MutableLiveData<Resource<List<Coin>>>()

    fun getCoins(): LiveData<Resource<List<Coin>>> {
        _coinsLiveData.postValue(Resource.Loading)
        CoroutineScope(IO).launch {
            try {
                val response = repository.getCoins()
                if (response.isSuccessful && response.code() == 200) {
                    _coinsLiveData.postValue(Resource.Success(response.body()!!))
                    return@launch
                }
                if (response.code() == 401) {
                    _coinsLiveData.postValue(Resource.Error(UnauthorizedRequestException("Illegal request")))
                    return@launch
                }
            }catch (e: Exception){
                _coinsLiveData.postValue(Resource.Error(e))
                return@launch
            }
            _coinsLiveData.postValue(Resource.Error(GeneralRequestException()))
        }
        return _coinsLiveData
    }*/




    operator fun invoke() = liveData(IO) {
        emit(Resource.Loading)
        try {
            val response = repository.getCoins()
            if (response.isSuccessful && response.code() == 200) {
                emit(Resource.Success(response.body()!!))
                Log.e("daata",response.body()!!.toString())
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
