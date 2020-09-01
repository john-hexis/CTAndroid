package com.ct.app.scene.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ct.guide.IViewModel
import com.ct.guide.IViewModelOutput

data class UserListViewModel(
    override var output: IViewModelOutput?
) : ViewModel(), IViewModel {
    constructor(): this(null)
}
