package com.ct.app.repository

import com.ct.app.repository.model.User
import io.reactivex.Maybe
import io.reactivex.Single

interface IUserRepository {
    fun authenticateUser(username: String, password: String): Single<Boolean>
    fun getUsers(): Maybe<List<User>>
    fun getUser(userId: Int): Single<User>
    fun saveUserDefault(userId: Int, username: String): Single<Boolean>
}