package com.ct.app.repository.remote

import com.ct.app.repository.model.User
import com.ct.guide.repository.remote.BaseRemoteDataSource
import com.ct.guide.repository.remote.IAPIService
import io.reactivex.Flowable

class UserRemoteDataSource() : BaseRemoteDataSource<UserAPIService>(), IUserRemoteDataSource {
    override var apiService: UserAPIService = this.createApiService()

    override fun getUsers(): Flowable<List<User>> {
        return this.apiService.getUsers()
    }
}