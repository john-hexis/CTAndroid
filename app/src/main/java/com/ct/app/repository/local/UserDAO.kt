package com.ct.app.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ct.app.repository.model.User
import io.reactivex.Single

@Dao
interface UserDAO {
    @Query("SELECT COUNT(*) FROM User WHERE username = :username AND password = :password")
    fun authenticateUser(username: String, password: String): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User): Single<Long>

    @Query("SELECT * FROM User WHERE id == :userId")
    fun getUser(userId: Int): Single<User>
}