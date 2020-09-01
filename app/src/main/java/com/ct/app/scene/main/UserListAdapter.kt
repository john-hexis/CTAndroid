package com.ct.app.scene.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ct.app.R
import com.ct.app.repository.model.User
import io.reactivex.processors.PublishProcessor
import kotlinx.android.synthetic.main.main_user_item.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {
    var users: ArrayList<UserItemHolder> = ArrayList()
//        set(value) {
//            field.clear()
//            field.addAll(value)
//            notifyDataSetChanged()
//        }
    var onClick: PublishProcessor<UserItemHolder> = PublishProcessor.create()

    data class UserItemHolder(val id: Int, val username: String, val fullname: String) {
        companion object {
            fun parse(user: User): UserItemHolder {
                return UserItemHolder(user.id, user.username, user.name)
            }
        }
    }

    inner class UserItemViewHolder(private var v: View): RecyclerView.ViewHolder(v) {
        fun bindData(item: UserItemHolder) {
            v.labelFullname.text = item.fullname
            v.labelUsername.text = item.username

            v.userItemView.setOnClickListener { onClick.onNext(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_user_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = users[position]
        holder.bindData(user)
    }

    override fun getItemCount(): Int {
        return this.users.count()
    }
}