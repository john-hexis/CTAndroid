package com.ct.app.repository

import com.ct.app.repository.local.IUserLocalDataSource
import com.ct.app.repository.model.User
import com.ct.app.repository.remote.IUserRemoteDataSource
import com.ct.guide.repository.BaseRepository
import io.reactivex.Maybe
import io.reactivex.Single
import java.lang.RuntimeException

class UserRepository(private val remote: IUserRemoteDataSource?, private val local: IUserLocalDataSource?) : BaseRepository(remote, local), IUserRepository {
    companion object {
        @Volatile private var repository: UserRepository? = null
        fun getInstance(remote: IUserRemoteDataSource?, local: IUserLocalDataSource?): UserRepository = repository ?: synchronized(this) {
            repository ?: UserRepository(remote, local).also {
                repository = it
            }
        }
    }

    override fun authenticateUser(username: String, password: String): Single<Boolean> {
        return this.local?.let { it.authenticateUser(username, password) }
            ?: run { Single.error(Error("Local isn't initialized in user repository.")) }
    }

    override fun getUsers(): Maybe<List<User>> {
        return Maybe.just(this.remote)
            .flatMap { thisRemote ->
                if (thisRemote != null) {
                    thisRemote.getUsers()
                            .toObservable()
                            .flatMapIterable { users -> users }
                            .flatMap { user -> saveUser(user).toObservable() }
                            .toList()
                            .toMaybe()
                } else {
                    throw (RuntimeException())
                } }
    }

    private data class SaveUsersHolder(val user: User, val isSuccess: Boolean)
    private fun saveUser(user: User): Maybe<User> {
        return Maybe.zip(Maybe.just(user), local?.saveUser(user)?.toMaybe(), { userList, result -> SaveUsersHolder(userList, result) })
            .flatMap { if (it.isSuccess) Maybe.just(it.user) else Maybe.error(Error("An error occurred during local save users.")) }
    }

    override fun getUser(userId: Int): Single<User> {
        return this.local?.let { it.getUser(userId) }
            ?: run { Single.error(Error("Local isn't initialized in user repository.")) }
    }

}