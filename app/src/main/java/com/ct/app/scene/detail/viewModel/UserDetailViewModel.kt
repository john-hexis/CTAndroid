package com.ct.app.scene.detail.viewModel

import androidx.lifecycle.ViewModel
import com.ct.guide.IViewModel
import com.ct.guide.IViewModelOutput

class UserDetailViewModel(
    var userId: Int,
    override var output: IViewModelOutput?
) : ViewModel(), IViewModel {
    constructor(): this(0, null)
}