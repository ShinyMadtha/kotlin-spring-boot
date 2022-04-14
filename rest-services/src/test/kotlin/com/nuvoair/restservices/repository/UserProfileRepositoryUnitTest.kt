package com.nuvoair.restservices.repository

import com.nuvoair.restservices.model.UserProfileTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class UserProfileRepositoryUnitTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    private val id = Math.random().toLong()

    @Test
    fun `given user, when user is requested by valid id, then matched user is returned`(){
        val userProfile = UserProfileTest.sampleTestUserProfile().copy(userId = id)
        entityManager.persist(userProfile)
        entityManager.flush()
        val userProfileIdFound = userProfileRepository.findByIdOrNull(userProfile.userId)
        assertEquals(userProfileIdFound, userProfile)

    }
}