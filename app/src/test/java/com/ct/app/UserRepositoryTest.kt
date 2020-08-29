package com.ct.app

import android.app.Instrumentation
import android.content.Context
import com.ct.app.repository.UserRepository
import com.ct.app.repository.local.UserDB
import com.ct.app.repository.local.UserLocalDataSource
import com.ct.app.repository.remote.UserAPIService
import com.ct.app.repository.remote.UserRemoteDataSource
import com.ct.guide.repository.remote.APIServiceUtility
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.junit.Before
import org.junit.Test
import androidx.test.core.app.ApplicationProvider

class UserRepositoryTest {
    private lateinit var userRepository: UserRepository
    private val context = ApplicationProvider.getApplicationContext<Context>()
    
    @Before
    fun setup() {
        userRepository =
            UserRepository(
                UserRemoteDataSource(
                    APIServiceUtility.newInstance<UserAPIService>().create(
                        UserAPIService::class.java
                    )
                ),
                UserLocalDataSource(UserDB.getDatabase(context).userDao())
            )
    }

    @Test
    fun correctCredential_authenticate_isPassed() {
        val username = "john.harries"
        val password = "123456"


    }

    @Test
    fun incorrectCredential_authenticate_isFailed() {
        val username = "john.harries"
        val password = "123456"


    }

    @Test
    fun userRepository_getUser_returnSomeUsers() {
        val d = this.userRepository.getUsers()
            .subscribe({ users ->
                assertEquals("$users Users is returned)", 10, users.size)
            }, { error ->
                fail("An error occured [${error.localizedMessage}]")
            })
    }
}