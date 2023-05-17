package com.thenexprojects.coinapplication102.common.exception

data class GeneralRequestException(val m: String = "Problem occurred during api request!"): Exception(m)

