package com.ct.app.scene.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.ct.app.repository.model.User
import com.ct.guide.IViewModelOutput

interface UserListViewModelOutput: IViewModelOutput {
    var users: MutableLiveData<List<User>>
}