package com.nuvoair.restservices.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDate
import javax.persistence.Column

@Entity
@Table(name = "users")
data class UserProfile(
        @Id
        @Column(name = "user_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Long?,
        @Column(name = "user_name", unique = true, nullable = false)
        val userName: String,
        @Column(name = "first_name", nullable = false)
        val firstName: String,
        @Column(name = "last_name", nullable = false)
        val lastName: String,
        @Column(name = "email_id", nullable = false)
        val emailId: String,
        @Column(name = "day_of_birth", nullable = false)
        val dayOfBirth: LocalDate,
        @Column(name = "address", nullable = false)
        val address: String,


)
