package com.nuvoair.restservices.model

import java.time.LocalDate

class UserProfileTest {
    companion object {

        private val id: Long = Math.random().toLong()
        private val mailId = Math.random()
        fun sampleTestUserProfile(): UserProfile {
            return UserProfile(
                    userId = id,
                    userName = "hanna.borgas",
                    firstName = "Hanna",
                    lastName = "Borgas",
                    emailId = "hanna.borgas$mailId@gmail.com",
                    dayOfBirth = LocalDate.now(),
                    address = "Riverview Street Brooklyn, NY 11228"
            )
        }

        fun SampleTestUpdatedUserProfile(): UserProfile{
            return UserProfile(
                    userId = id,
                    userName = "hanna.borgas",
                    firstName = "Hanna",
                    lastName = "Borgas",
                    emailId = "hanna.borgas@gmail.com",
                    dayOfBirth = LocalDate.now(),
                    address = "4 Riverview Street Brooklyn, NY 11228"
            )
        }
    }
}