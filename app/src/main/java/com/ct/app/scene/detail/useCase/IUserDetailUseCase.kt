package com.ct.app.scene.detail.useCase

import com.ct.app.scene.detail.viewModel.UserDetailViewModel
import com.ct.guide.IUseCase

interface IUserDetailUseCase: IUseCase {
    fun getUser(viewModel: UserDetailViewModel)
}