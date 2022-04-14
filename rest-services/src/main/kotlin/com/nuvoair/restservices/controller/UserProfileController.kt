package com.nuvoair.restservices.controller

import com.nuvoair.restservices.model.UserProfile
import com.nuvoair.restservices.service.UserProfileService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.validation.Payload

@RestController
class UserProfileController(private val userProfileService: UserProfileService) {

    @GetMapping("/users")
    fun getAllUserProfiles(): List<UserProfile> = userProfileService.getAllUserProfiles()

    @GetMapping("/users/{id}")
    fun getUserProfileById(@PathVariable("id") userId: Long): UserProfile =
            userProfileService.getUserProfileById(userId)

    @PostMapping("/users")
    fun createUserProfile(@RequestBody payload: UserProfile): UserProfile = userProfileService.createUserProfile(payload)

    @PutMapping("/users/{id}")
    fun updateUserProfileById(@PathVariable("id") userId: Long, @RequestBody payload: UserProfile): UserProfile =
            userProfileService.updateUserProfileById(userId, payload)

    @DeleteMapping("/users/{id}")
    fun deleteUserProfileById(@PathVariable("id") userId: Long): Unit = userProfileService.deleteUserProfileById(userId)
}