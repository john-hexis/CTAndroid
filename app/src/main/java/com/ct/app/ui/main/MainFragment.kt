package com.ct.app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.ct.app.R
import com.ct.app.guide.IFragment
import com.ct.app.ui.main.viewModel.UserListViewModel
import com.ct.app.ui.main.viewModel.UserListViewModelOutput

class MainFragment : Fragment(), IFragment, UserListViewModelOutput {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val userViewModel: UserListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        this.setViewModelOutputObserver()
    }

    //region IFragment
    override fun setViewModelOutputObserver() {
        this.userViewModel.output = this
        (this.userViewModel.output as UserListViewModelOutput).users.observe(viewLifecycleOwner, { this::updateList })
    }
    //endregion

    //region MainViewModelOutput
    override lateinit var users: MutableLiveData<List<String>>
    //endregion

    //region Private Handler
    private fun updateList(userList: List<String>) {
        print("userList: $userList")
    }
    //endregion
}