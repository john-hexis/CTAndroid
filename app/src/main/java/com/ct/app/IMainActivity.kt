package com.ct.app

interface IMainActivity {
    //region AuthLogin
    fun authLogin_gotoMain()
    fun authLogin_showToast(message: String)
    //endregion

    //region Main
    fun main_showToast(message: String)
    fun main_gotoDetail(id: Int)
    //endregion

    //region Detail
    fun detail_showToast(message: String)
    //endregion
}