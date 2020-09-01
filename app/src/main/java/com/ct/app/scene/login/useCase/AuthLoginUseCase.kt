package com.ct.app.scene.login.useCase

import com.ct.app.repository.UserRepository
import com.ct.app.scene.login.viewModel.AuthLoginViewModel
import com.ct.app.scene.login.viewModel.AuthLoginViewModelOutput
import com.ct.app.scene.login.viewModel.AuthenticationStatus
import com.ct.guide.repository.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthLoginUseCase: IAuthLoginUseCase {
    private lateinit var userRepository: UserRepository
    private var disposeBag: CompositeDisposable = CompositeDisposable()

    protected fun finalize() {
        this.disposeBag.clear()
        this.disposeBag.dispose()
    }

    override fun authLogin(viewModel: AuthLoginViewModel) {
        val d = this.userRepository.authenticateUser(viewModel.username, viewModel.password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ success ->
                if (success) (viewModel.output as AuthLoginViewModelOutput).authStatus.value = AuthenticationStatus(AuthenticationStatus.Type.Success, "")
                else (viewModel.output as AuthLoginViewModelOutput).authStatus.value = AuthenticationStatus(AuthenticationStatus.Type.Unsuccessful, "Username or password is wrong.")
            }, { error ->
                (viewModel.output as AuthLoginViewModelOutput).authStatus.value = AuthenticationStatus(AuthenticationStatus.Type.Error, error.localizedMessage)
            })
        this.disposeBag.add(d)
    }

    override fun createDefaultLogin() {
        val d = this.userRepository.saveUserDefault(99999, "john.harries")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun defineRepository(repositories: List<BaseRepository>) {
        for(repo in repositories) {
            when(repo) {
                is UserRepository -> this.userRepository = repo
            }
        }
    }
}