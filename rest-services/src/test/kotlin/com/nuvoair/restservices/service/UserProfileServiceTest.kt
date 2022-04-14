package com.nuvoair.restservices.service

import com.nhaarman.mockitokotlin2.doNothing
import com.nuvoair.restservices.repository.UserProfileRepository
import com.nhaarman.mockitokotlin2.mock
import com.nuvoair.restservices.exception.UserProfileNotFoundException
import com.nuvoair.restservices.model.UserProfileTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import java.util.*

class UserProfileServiceTest {

    private val userProfileRepository: UserProfileRepository = mock { }
    private val classUnderTest = UserProfileService(userProfileRepository)
    private val id = 13.toLong()

    @Test
    fun `given user profile, when list of all user profiles are requested, then all users are returned`(){
        val userProfileList = listOf(
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile(),
                UserProfileTest.sampleTestUserProfile()
        )
        Mockito.`when`(userProfileRepository.findAll()).thenReturn(userProfileList)
        assertEquals(4, classUnderTest.getAllUserProfiles().size)
    }

    @Test
    fun `given user, when user is requested by valid id, then matched user is returned`(){

        Mockito.`when`(userProfileRepository.findById(id)).thenReturn(
                Optional.of(UserProfileTest.sampleTestUserProfile().copy(userId = id))
        )
        val result = classUnderTest.getUserProfileById(id)
        assertEquals(id, result.userId)
    }

    @Test
    fun `given user, when user is requested by invalid id, throws expection`(){
        val invalidId: Long = Math.random().toLong()

        assertThrows<UserProfileNotFoundException> { classUnderTest.getUserProfileById(invalidId) }
    }

    @Test
    fun `given new user details, when user profile is created, then returns new user profile`(){
        Mockito.`when`(userProfileRepository.save(UserProfileTest.sampleTestUserProfile())).thenReturn(
                UserProfileTest.sampleTestUserProfile()
        )
        val result = classUnderTest.createUserProfile(UserProfileTest.sampleTestUserProfile())
        assertEquals("Hanna", result.firstName)
        assertEquals("hanna.borgas", result.userName)
    }

    @Test
    fun `given update user details, when user is updated by valid id, then it returns the updated user details`(){
        Mockito.`when`(userProfileRepository.existsById(id)).thenReturn(true)
        Mockito.`when`(userProfileRepository.save(UserProfileTest.SampleTestUpdatedUserProfile().copy(userId = id))).thenReturn(
                UserProfileTest.SampleTestUpdatedUserProfile().copy(userId = id)
        )

        val result = classUnderTest.updateUserProfileById(id, UserProfileTest.SampleTestUpdatedUserProfile().copy(userId = id))
        assertEquals("hanna.borgas@gmail.com", result.emailId)
        assertEquals("4 Riverview Street Brooklyn, NY 11228", result.address)
    }

    @Test
    fun `given user, when user is deleted by id, then it returns the deleted user details`(){
        Mockito.`when`(userProfileRepository.findById(id)).thenReturn(
                Optional.of(UserProfileTest.sampleTestUserProfile().copy(userId = id))
        )
        doNothing().`when`(userProfileRepository).delete(UserProfileTest.sampleTestUserProfile().copy(userId = id))

        val result = classUnderTest.deleteUserProfileById(id)
        assertEquals("", result)
    }


}