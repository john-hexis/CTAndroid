package com.ct.guide.repository

import com.ct.guide.repository.local.ILocalDataStore
import com.ct.guide.repository.remote.IRemoteDataSource

abstract class BaseRepository(private val remote: IRemoteDataSource?, private val local: ILocalDataStore?) {

}