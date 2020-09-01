package com.ct.app.scene.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.ct.app.IMainActivity
import com.ct.app.R
import com.ct.app.repository.UserRepository
import com.ct.app.repository.local.UserDB
import com.ct.app.repository.local.UserLocalDataSource
import com.ct.app.repository.remote.UserRemoteDataSource
import com.ct.app.scene.login.useCase.AuthLoginUseCase
import com.ct.guide.IFragment
import com.ct.app.scene.login.viewModel.AuthLoginViewModel
import com.ct.app.scene.login.viewModel.AuthLoginViewModelOutput
import com.ct.app.scene.login.viewModel.AuthenticationStatus
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment(private val mainActivity: IMainActivity) : Fragment(), IFragment, AuthLoginViewModelOutput {

    companion object {
        fun newInstance(mainActivity: IMainActivity) = LoginFragment(mainActivity)
    }

    private val authLoginViewModel: AuthLoginViewModel by viewModels()
    private lateinit var authLoginUseCase: AuthLoginUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.authLoginUseCase = AuthLoginUseCase()
        this.authLoginUseCase.defineRepository(arrayListOf(UserRepository(UserRemoteDataSource(), UserLocalDataSource(
            UserDB.getDatabase(this.requireContext()).userDao()))))

        this.authLoginUseCase.createDefaultLogin()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonSubmit.setOnClickListener { v -> buttonSubmitOnClick(v) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.setViewModelOutputObserver()
    }

    //region IFragment
    override fun setViewModelOutputObserver() {
        this.authStatus = MutableLiveData()
        this.authStatus.observe(viewLifecycleOwner, { onAuthStatusChanged() })
        this.authLoginViewModel.output = this
    }
    //endregion

    //region AuthLoginViewModelOutput
    override lateinit var authStatus: MutableLiveData<AuthenticationStatus>
    override lateinit var errorMessage: MutableLiveData<String>

    private fun onAuthStatusChanged() {
        when(authStatus.value?.result) {
            AuthenticationStatus.Type.Success -> { mainActivity.authLogin_gotoMain() }
            AuthenticationStatus.Type.Unsuccessful -> { mainActivity.authLogin_showToast(authStatus.value?.message ?: "Authentication unsuccessful.") }
            AuthenticationStatus.Type.Error -> { mainActivity.authLogin_showToast(authStatus.value?.message ?: "Authentication got error.") }
            else -> { mainActivity.authLogin_showToast(authStatus.value?.message ?: "Authentication unknown.") }
        }
    }
    //endregion

    //region UI Component Handler
    private fun buttonSubmitOnClick(sender: View) {
        this.authLoginViewModel.username = editTextUsername.text.toString()
        this.authLoginViewModel.password = editTextPassword.text.toString()
        this.authLoginUseCase.authLogin(authLoginViewModel)
    }
    //endregion
}