package com.ct.app.scene.detail

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
import com.ct.app.repository.model.User
import com.ct.app.repository.remote.UserRemoteDataSource
import com.ct.app.scene.detail.useCase.UserDetailUseCase
import com.ct.app.scene.detail.viewModel.UserDetailViewModel
import com.ct.app.scene.detail.viewModel.UserDetailViewModelOutput
import com.ct.app.scene.login.useCase.AuthLoginUseCase
import com.ct.guide.IFragment
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment(private val mainActivity: IMainActivity, private val userId: Int): Fragment(), IFragment, UserDetailViewModelOutput {

    companion object {
        fun newInstance(mainActivity: IMainActivity, userId: Int) = DetailFragment(mainActivity, userId)
    }

    private val userDetailViewModel: UserDetailViewModel by viewModels()
    private lateinit var userDetailUseCase: UserDetailUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.userDetailUseCase = UserDetailUseCase()
        this.userDetailUseCase.defineRepository(arrayListOf(
            UserRepository(
                UserRemoteDataSource(), UserLocalDataSource(
            UserDB.getDatabase(this.requireContext()).userDao())
            )
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.setViewModelOutputObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.userDetailViewModel.userId = userId
        this.userDetailUseCase.getUser(userDetailViewModel)
    }

    override fun setViewModelOutputObserver() {
        this.errorMessage = MutableLiveData()
        this.user = MutableLiveData()
        this.errorMessage.observe(viewLifecycleOwner, { errorMessage -> this.showErrorToast(errorMessage) })
        this.user.observe(viewLifecycleOwner, { user -> this.showUserDetail(user) })
        this.userDetailViewModel.output = this
    }

    override lateinit var user: MutableLiveData<User>
    override lateinit var errorMessage: MutableLiveData<String>

    private fun showUserDetail(user: User) {
        this.labelFullname.text = user.name
        this.labelUsername.text = user.username
    }

    private fun showErrorToast(message: String) {
        this.mainActivity.detail_showToast(message)
    }
}