package com.ct.app.repository.remote

import com.ct.app.repository.model.User
import com.ct.guide.repository.remote.IRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Maybe

interface IUserRemoteDataSource: IRemoteDataSource {
    fun getUsers(): Flowable<List<User>>
}