package com.ct.app.repository

import com.ct.app.repository.model.User
import com.ct.guide.repository.IRequest
import io.reactivex.Flowable

interface IUserRepository {
    fun authenticateUser(username: String, password: String): Flowable<Boolean>
    fun getUsers(): Flowable<List<User>>
}