package com.ct.app.scene.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ct.app.IMainActivity
import com.ct.app.R
import com.ct.app.repository.UserRepository
import com.ct.app.repository.local.UserDB
import com.ct.app.repository.local.UserLocalDataSource
import com.ct.app.repository.model.User
import com.ct.app.repository.remote.UserRemoteDataSource
import com.ct.app.scene.login.useCase.AuthLoginUseCase
import com.ct.app.scene.main.useCase.UserListUseCase
import com.ct.guide.IFragment
import com.ct.app.scene.main.viewModel.UserListViewModel
import com.ct.app.scene.main.viewModel.UserListViewModelOutput
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment(private val mainActivity: IMainActivity) : Fragment(), IFragment, UserListViewModelOutput {

    companion object {
        fun newInstance(mainActivity: IMainActivity) = MainFragment(mainActivity)
    }

    private val userViewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var userListUseCase: UserListUseCase
    private val disposableBag: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.userListUseCase = UserListUseCase()
        this.userListUseCase.defineRepository(arrayListOf(
            UserRepository(
                UserRemoteDataSource(), UserLocalDataSource(
            UserDB.getDatabase(this.requireContext()).userDao())
            )
        ))
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.setViewModelOutputObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.userListAdapter = UserListAdapter()
        recyclerView.adapter = userListAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.disposableBag.add(
            userListAdapter.onClick
                .subscribe { holder ->
                    mainActivity.main_gotoDetail(holder.id)
                }
        )
        this.userListUseCase.getUserList(userViewModel)
    }

    //region IFragment
    override fun setViewModelOutputObserver() {
        this.users = MutableLiveData()
        this.errorMessage = MutableLiveData()
        this.users.observe(viewLifecycleOwner, { users -> this.updateList(users) })
        this.errorMessage.observe(viewLifecycleOwner, { errorMessage -> this.showErrorMessage(errorMessage) })
        this.userViewModel.output = this
    }
    //endregion

    //region MainViewModelOutput
    override lateinit var users: MutableLiveData<List<User>>
    override lateinit var errorMessage: MutableLiveData<String>
    //endregion

    //region Private Handler
    private fun updateList(userList: List<User>) {
        this.userListAdapter.users.clear()
        this.userListAdapter.users.addAll(userList.map { user -> UserListAdapter.UserItemHolder.parse(user) })
        this.userListAdapter.notifyDataSetChanged()
    }

    private fun showErrorMessage(message: String) {
        this.mainActivity.main_showToast(message)
    }
    //endregion
}