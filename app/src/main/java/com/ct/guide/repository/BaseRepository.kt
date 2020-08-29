package com.ct.guide.repository

import com.ct.guide.repository.local.ILocalDataStore
import com.ct.guide.repository.remote.IRemoteDataSource

abstract class IRepository(remote: IRemoteDataSource?, local: ILocalDataStore?) {

}