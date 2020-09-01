package com.ct.app.scene.detail.useCase

import com.ct.app.repository.UserRepository
import com.ct.app.scene.detail.viewModel.UserDetailViewModel
import com.ct.app.scene.detail.viewModel.UserDetailViewModelOutput
import com.ct.guide.repository.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDetailUseCase: IUserDetailUseCase {
    private lateinit var userRepository: UserRepository
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    protected fun finalize() {
        this.disposeBag.clear()
        this.disposeBag.dispose()
    }

    override fun getUser(viewModel: UserDetailViewModel) {
        val d = this.userRepository.getUser(viewModel.userId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ user ->
                (viewModel.output as UserDetailViewModelOutput).user.value = user
            }, { error ->
                (viewModel.output as UserDetailViewModelOutput).errorMessage.value = "An error occurred [${error.localizedMessage}]"
            })
    }

    override fun defineRepository(repositories: List<BaseRepository>) {
        for (repo in repositories) {
            when(repo) {
                is UserRepository -> userRepository = repo
            }
        }
    }
}