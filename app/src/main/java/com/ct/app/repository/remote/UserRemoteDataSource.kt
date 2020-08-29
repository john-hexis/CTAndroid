package com.ct.app.repository.remote

import com.ct.app.repository.model.User
import com.ct.guide.repository.remote.BaseRemoteDataSource
import com.ct.guide.repository.remote.IAPIService
import io.reactivex.Flowable

class UserRemoteDataSource(override val apiService: UserAPIService) : BaseRemoteDataSource<UserAPIService>(), IUserRemoteDataSource {
    override fun getUser(): Flowable<List<User>> {
        return this.apiService.getUsers()
    }
}