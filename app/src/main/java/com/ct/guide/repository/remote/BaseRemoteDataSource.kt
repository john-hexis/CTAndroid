package com.ct.guide.repository.remote

abstract class BaseRemoteDataSource<T:IAPIService>: IRemoteDataSource {
    protected abstract val apiService: IAPIService
    /**
     * Create new API service, only can create 1 instance for each service to avoid memory leaks.
     * */
    internal inline fun <reified T : IAPIService> createApiService(): T {
        return APIServiceUtility.newInstance<T>().create(T::class.java)
    }
}