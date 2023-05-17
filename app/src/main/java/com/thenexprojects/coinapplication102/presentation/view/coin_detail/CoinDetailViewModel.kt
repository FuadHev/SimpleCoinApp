package com.thenexprojects.coinapplication102.presentation.view.coin_detail

import androidx.lifecycle.ViewModel
import com.thenexprojects.coinapplication102.domain.use_case.GetCoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val getCoinByIdUseCase: GetCoinByIdUseCase):ViewModel() {



    fun getCoinById(id:String)=getCoinByIdUseCase.invoke(id)

}