package com.ct.app.repository.local

import com.ct.app.repository.model.User
import io.reactivex.Single

class UserLocalDataSource(private val userDAO: UserDAO): IUserLocalDataSource {
    override fun authenticateUser(username: String, password: String): Single<Boolean> {
        return this.userDAO.authenticateUser(username, password)
            .flatMap { existence -> Single.just(existence > 0 ) }
    }

    override fun saveUser(user: User): Single<Boolean> {
        return this.userDAO.saveUser(user)
            .map { ref -> ref > 0 }
    }

    override fun getUser(userID: Int): Single<User> {
        return this.userDAO.getUser(userID)
    }
}