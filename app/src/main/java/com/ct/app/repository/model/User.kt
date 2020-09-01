package com.ct.app.repository.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val username: String,
    val password: String = "123456",
    val email: String,
    @Embedded(prefix = "Address_")val address: Address,
    val phone: String,
    val website: String,
    @Embedded(prefix = "Company_") val company: Company
)