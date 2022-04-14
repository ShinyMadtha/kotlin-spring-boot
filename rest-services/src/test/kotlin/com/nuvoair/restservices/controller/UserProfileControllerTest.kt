package com.nuvoair.restservices.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nuvoair.restservices.model.UserProfileTest
import com.nuvoair.restservices.service.UserProfileService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserProfileController:: class])
@AutoConfigureMockMvc(addFilters = false)
class UserProfileControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var userProfileService: UserProfileService

    private val id = Math.random().toLong()

    @Test
    fun `Given url, when GET all User Profile is called, then returns 200 `(){
        val userProfileList = listOf(
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile()
        )
        Mockito.`when`(userProfileService.getAllUserProfiles()).thenReturn(userProfileList)

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.[*].userId").isNotEmpty)
    }

    @Test
    fun `Given a userId, when GET user profile by Id is called, then returns 200`(){
        val userProfile = UserProfileTest.sampleTestUserProfile().copy(userId = id)

        Mockito.`when`(userProfileService.getUserProfileById(id)).thenReturn(userProfile)

        mockMvc.perform(get("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.userId").value(id))
    }

    @Test
    fun `Given a new user profile, when POST create user profile is called, then returns 200`(){
        val userProfile = UserProfileTest.sampleTestUserProfile().copy(userId = id)

        Mockito.`when`(userProfileService.createUserProfile(userProfile)).thenReturn(userProfile)

        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(userProfile))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.userId").value(id))
                .andExpect(jsonPath("$.firstName").value(userProfile.firstName))
    }

    @Test
    fun `Given data to update user profile, when PUT update user profile is called, then returns 200`(){
        val userProfile = UserProfileTest.SampleTestUpdatedUserProfile().copy(userId = id)

        Mockito.`when`(userProfileService.updateUserProfileById(id, userProfile)).thenReturn(userProfile)

        mockMvc.perform(put("/users/{id}", id)
                .content(objectMapper.writeValueAsString(userProfile))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.userId").value(id))
                .andExpect(jsonPath("$.firstName").value(userProfile.firstName))
                .andExpect(jsonPath("$.lastName").value(userProfile.lastName))
    }

    @Test
    fun `Given a userId, when DELETE user profile by Id is called, then returns 200`(){
//        val userProfile = UserProfileTest.sampleTestUserProfile().copy(userId = id)

        Mockito.`when`(userProfileService.deleteUserProfileById(id))

        mockMvc.perform(delete("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }



}