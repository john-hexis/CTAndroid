package com.ct.app.scene.detail.viewModel

import androidx.lifecycle.MutableLiveData
import com.ct.app.repository.model.User
import com.ct.guide.IViewModelOutput

interface UserDetailViewModelOutput: IViewModelOutput {
    var user: MutableLiveData<User>
}