package com.thenexprojects.coinapplication102.presentation.view.coin_list

import androidx.lifecycle.ViewModel
import com.thenexprojects.coinapplication102.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase,
):ViewModel() {

    fun getCoins() = getCoinsUseCase()

//    fun insertCoin(coin: Coin) = insertCoinUseCase(coin)

//    fun getCoinsSecond(search: String) = getCoinsUseCase.getCoins()

}