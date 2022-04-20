package com.example.project1withvolley.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1withvolley.databinding.ItemUserViewBinding
import com.example.project1withvolley.model.User
import com.squareup.picasso.Picasso

class UserAdapter(var list: List<User>): RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(var itemUserViewBinding: ItemUserViewBinding): RecyclerView.ViewHolder(itemUserViewBinding.root){

        fun onBind(user:User) {
            Picasso.get().load(user.avatar_url).into(itemUserViewBinding.ivAvatar)
            itemUserViewBinding.tvLogin.text = user.login
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val Vh = Vh(ItemUserViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return Vh
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
       holder.onBind(list[position])
    }

    override fun getItemCount(): Int= list.size


}