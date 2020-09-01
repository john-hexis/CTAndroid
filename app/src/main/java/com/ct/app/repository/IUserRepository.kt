package com.ct.app.repository

import com.ct.app.repository.model.User
import com.ct.guide.repository.IRequest
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface IUserRepository {
    fun authenticateUser(username: String, password: String): Single<Boolean>
    fun getUsers(): Maybe<List<User>>
    fun getUser(userId: Int): Single<User>
}