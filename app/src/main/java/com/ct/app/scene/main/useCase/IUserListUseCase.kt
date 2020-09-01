package com.ct.app.scene.main.useCase

import com.ct.app.repository.model.User
import com.ct.app.scene.main.viewModel.UserListViewModel
import com.ct.guide.IUseCase
import io.reactivex.Maybe

interface IUserListUseCase: IUseCase {
    fun getUserList(viewModel: UserListViewModel)
}