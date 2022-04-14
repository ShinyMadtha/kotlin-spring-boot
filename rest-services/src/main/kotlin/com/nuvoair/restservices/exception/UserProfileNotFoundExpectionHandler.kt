package com.nuvoair.restservices.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserProfileNotFoundExpectionHandler {
    @ExceptionHandler
    fun handleException(exc : UserProfileNotFoundException) : ResponseEntity<UserProfileErrorResponse> {
        val userProfileErrorResponse = UserProfileErrorResponse(HttpStatus.NOT_FOUND.value(),
                exc.message,
                System.currentTimeMillis())

        return ResponseEntity(userProfileErrorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleGenericException(exc : Exception) : ResponseEntity<UserProfileErrorResponse> {
        val userProfileErrorResponse = UserProfileErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.message,
                System.currentTimeMillis())

        return ResponseEntity(userProfileErrorResponse, HttpStatus.BAD_REQUEST)
    }
}