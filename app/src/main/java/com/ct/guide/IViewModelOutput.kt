package com.ct.guide

import androidx.lifecycle.MutableLiveData

interface IViewModelOutput {
    var errorMessage: MutableLiveData<String>
}