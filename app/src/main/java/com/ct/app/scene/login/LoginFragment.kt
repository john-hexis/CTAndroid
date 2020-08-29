package com.ct.app.scene.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ct.app.R
import com.ct.guide.IFragment
import com.ct.app.scene.login.viewModel.AuthLoginViewModel

class LoginFragment : Fragment(), IFragment {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val authLoginViewModel: AuthLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.setViewModelOutputObserver()
    }

    //region IFragment
    override fun setViewModelOutputObserver() {

    }
    //endregion
}