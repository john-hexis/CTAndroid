package com.ct.app.repository

import com.ct.app.repository.model.User
import com.ct.app.repository.remote.UserRemoteDataSource
import com.ct.guide.repository.BaseRepository
import com.ct.guide.repository.local.ILocalDataStore
import com.ct.guide.repository.remote.IRemoteDataSource
import io.reactivex.Flowable

class UserRepository(private val remote: IRemoteDataSource?, private val local: ILocalDataStore?) : BaseRepository(remote, local), IUserRepository {
    companion object {
        @Volatile private var repository: UserRepository? = null
        fun getInstance(remote: IRemoteDataSource?, local: ILocalDataStore?): UserRepository = repository ?: synchronized(this) {
            repository ?: UserRepository(remote as IRemoteDataSource, local).also {
                repository = it
            }
        }
    }

    override fun authenticateUser(username: String, password: String): Flowable<Boolean> {
        return Flowable.just(true)
    }

    override fun getUsers(): Flowable<List<User>> {
        return (this.remote as UserRemoteDataSource).getUser()
    }
}