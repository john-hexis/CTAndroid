package com.ct.app.repository.local

import com.ct.app.repository.model.User
import com.ct.guide.repository.local.ILocalDataStore
import io.reactivex.Single

interface IUserLocalDataSource: ILocalDataStore {
    fun authenticateUser(username: String, password: String): Single<Boolean>
    fun saveUser(user: User): Single<Boolean>
    fun getUser(userID: Int): Single<User>
}