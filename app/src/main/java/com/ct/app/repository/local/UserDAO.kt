package com.ct.app.repository.local

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UserDAO {
    @Query("SELECT password FROM User WHERE username = :username")
    fun getPassword(username: String): Single<String>
}