package com.ct.app.scene.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ct.guide.IViewModel
import com.ct.guide.IViewModelOutput

class UserListViewModel(
    var ID: MutableLiveData<String>
) : ViewModel(), IViewModel {
    private lateinit var vmOutput: UserListViewModelOutput
    override var output: IViewModelOutput
        get() = vmOutput
        set(value) { vmOutput = value as UserListViewModelOutput }
}
