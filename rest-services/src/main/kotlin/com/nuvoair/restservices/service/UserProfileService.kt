package com.nuvoair.restservices.service

import com.nuvoair.restservices.exception.UserProfileNotFoundException
import com.nuvoair.restservices.model.UserProfile
import com.nuvoair.restservices.repository.UserProfileRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class UserProfileService(private val userProfileRepository: UserProfileRepository) {

    fun getAllUserProfiles(): List<UserProfile> = userProfileRepository.findAll()

    fun getUserProfileById(userId: Long): UserProfile = userProfileRepository.findById(userId)
            .orElseThrow { UserProfileNotFoundException(HttpStatus.NOT_FOUND, "No User Profile was found for the Id") }

    fun createUserProfile(userProfile: UserProfile): UserProfile = userProfileRepository.save(userProfile)

    fun updateUserProfileById(userId: Long, userProfile: UserProfile): UserProfile {
        return if (userProfileRepository.existsById(userId)) {
            userProfileRepository.save(
                    UserProfile(
                            userId = userProfile.userId,
                            userName = userProfile.userName,
                            firstName = userProfile.firstName,
                            lastName = userProfile.lastName,
                            emailId = userProfile.emailId,
                            dayOfBirth = userProfile.dayOfBirth,
                            address = userProfile.address
                    )
            )
        } else throw UserProfileNotFoundException(HttpStatus.NOT_FOUND, "No User Profile was found for the Id")
    }

    fun deleteUserProfileById(userId: Long){
        return if (userProfileRepository.existsById(userId)){
            userProfileRepository.deleteById(userId)
        } else throw UserProfileNotFoundException(HttpStatus.NOT_FOUND, "No User Profile was found for the Id")
    }

}