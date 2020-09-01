package com.ct.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.ct.app.scene.login.LoginFragment
import com.ct.app.scene.main.MainFragment

class MainActivity : AppCompatActivity(), IMainActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance(this))
                    .commitNow()
        }
    }

    override fun authLogin_gotoMain() {
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance(this))
            .commitNow()
    }

    override fun authLogin_showToast(message: String) {
        showToast(message)
    }

    override fun main_showToast(message: String) {
        showToast(message)
    }

    override fun main_gotoDetail(id: Int) {
        supportActionBar?.show()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance(this))
            .commitNow()
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }
}