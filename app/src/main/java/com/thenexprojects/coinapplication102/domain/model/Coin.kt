package com.thenexprojects.coinapplication102.domain.model


data class Coin(
    val id: String,
    val is_active: Boolean,
    val any: Any,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String,
    var isExpandable:Boolean =false
)
