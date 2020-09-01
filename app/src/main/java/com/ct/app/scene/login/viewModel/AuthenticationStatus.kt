package com.ct.app.scene.login.viewModel

data class AuthenticationStatus(
    var result: Type,
    var message: String
) {
    enum class Type {
        Success,
        Unsuccessful,
        Error,
    }
}