package com.ct.app.scene.main.useCase

import com.ct.app.repository.UserRepository
import com.ct.app.repository.model.User
import com.ct.app.scene.main.viewModel.UserListViewModel
import com.ct.app.scene.main.viewModel.UserListViewModelOutput
import com.ct.guide.repository.BaseRepository
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListUseCase: IUserListUseCase {
    private lateinit var userRepository: UserRepository
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    protected fun finalize() {

    }

    override fun getUserList(viewModel: UserListViewModel) {
        val d = this.userRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ users ->
                (viewModel.output as UserListViewModelOutput).users.value = users
            }, { error ->
                (viewModel.output as UserListViewModelOutput).errorMessage.value = "An error occurred [${error.localizedMessage}]"
            })
        this.disposeBag.add(d)
    }

    override fun defineRepository(repositories: List<BaseRepository>) {
        for (repo in repositories) {
            when(repo) {
                is UserRepository -> this.userRepository = repo
            }
        }
    }
}