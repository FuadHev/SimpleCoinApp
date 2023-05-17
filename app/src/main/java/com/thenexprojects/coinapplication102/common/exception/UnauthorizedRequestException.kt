package com.thenexprojects.coinapplication102.common.exception

data class UnauthorizedRequestException(val m: String = "Unauthorized request"): Exception(m)