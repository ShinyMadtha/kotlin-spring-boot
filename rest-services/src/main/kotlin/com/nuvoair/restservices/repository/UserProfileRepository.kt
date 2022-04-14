package com.nuvoair.restservices.repository

import com.nuvoair.restservices.model.UserProfile
import org.springframework.data.jpa.repository.JpaRepository

interface UserProfileRepository : JpaRepository<UserProfile, Long>
