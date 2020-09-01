package com.ct.app.scene.login.useCase

import com.ct.app.scene.login.viewModel.AuthLoginViewModel
import com.ct.guide.IUseCase

interface IAuthLoginUseCase: IUseCase {
    fun authLogin(viewModel: AuthLoginViewModel)
    fun createDefaultLogin()
}