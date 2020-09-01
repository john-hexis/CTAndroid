package com.ct.guide

import com.ct.guide.repository.BaseRepository

interface IUseCase {
    fun defineRepository(repositories:List<BaseRepository>)
}