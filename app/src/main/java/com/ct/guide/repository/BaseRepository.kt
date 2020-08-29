package com.ct.guide.repository

import com.ct.guide.repository.local.ILocalDataStore
import com.ct.guide.repository.remote.IRemoteDataSource

abstract class BaseRepository(remote: IRemoteDataSource?, local: ILocalDataStore?) {

}