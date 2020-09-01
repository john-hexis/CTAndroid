package com.ct.app.scene.login.viewModel

import androidx.lifecycle.ViewModel
import com.ct.guide.IViewModel
import com.ct.guide.IViewModelOutput

data class AuthLoginViewModel(
    var username: String,
    var password: String,
    override var output: IViewModelOutput?
) : ViewModel(), IViewModel {
    constructor() : this("", "", null)
}