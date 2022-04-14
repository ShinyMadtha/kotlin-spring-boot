package com.nuvoair.restservices.exception

import org.springframework.http.HttpStatus

class UserProfileNotFoundException(val statusCode: HttpStatus, val reason: String): Exception(reason)
