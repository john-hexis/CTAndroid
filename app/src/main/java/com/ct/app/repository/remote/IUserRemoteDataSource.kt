package com.ct.app.repository.remote

import com.ct.app.repository.model.User
import com.ct.guide.repository.remote.IRemoteDataSource
import io.reactivex.Flowable

interface IUserRemoteDataSource: IRemoteDataSource {
    fun getUser(): Flowable<List<User>>
}