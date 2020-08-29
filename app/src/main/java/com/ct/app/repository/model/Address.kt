package com.ct.app.repository.model

import androidx.room.Embedded

data class Address (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Embedded(prefix = "AddressGeo_") val geo: Geo
)