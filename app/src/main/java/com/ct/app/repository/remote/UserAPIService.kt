package com.ct.app.repository.remote

import com.ct.app.repository.model.User
import com.ct.guide.repository.remote.IAPIService
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserAPIService: IAPIService {
    @GET("/users")
    fun getUsers(): Flowable<List<User>>
}