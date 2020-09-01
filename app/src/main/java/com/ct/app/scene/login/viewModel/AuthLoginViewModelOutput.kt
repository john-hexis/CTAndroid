package com.ct.app.scene.login.viewModel

import androidx.lifecycle.MutableLiveData
import com.ct.guide.IViewModelOutput

interface AuthLoginViewModelOutput: IViewModelOutput {
    var authStatus: MutableLiveData<AuthenticationStatus>
}