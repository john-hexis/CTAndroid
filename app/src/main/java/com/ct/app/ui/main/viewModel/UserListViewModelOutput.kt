package com.ct.app.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.ct.app.guide.IViewModelOutput

interface UserListViewModelOutput: IViewModelOutput {
    var users: MutableLiveData<List<String>>
}