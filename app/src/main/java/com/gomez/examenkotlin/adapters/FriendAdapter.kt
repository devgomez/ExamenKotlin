package com.gomez.examenkotlin.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gomez.examenkotlin.FriendsActivity
import com.gomez.examenkotlin.R
import com.gomez.examenkotlin.models.UserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_friend.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class FriendAdapter(private var data: List<UserResponse>, private val listener: FriendsActivity):RecyclerView.Adapter<FriendAdapter.FriendHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.FriendHolder {
        val inflatedView = parent.inflate(R.layout.item_friend, false)
        return FriendAdapter.FriendHolder(inflatedView)
    }
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(userList: List<UserResponse>) {
        this.data = userList
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FriendAdapter.FriendHolder, position: Int) {
        val user: UserResponse = this.data[position]
        holder.itemView.tv_friends_item_name.text = user.username

        if(!user.image.isBlank()) {
            Picasso.get()
                .load(user.image)
                .into(holder.itemView.iv_friends_photo)
        }

        holder.itemView.setOnClickListener{listener.onItemClickListener(user)}
    }

    class FriendHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()

            }
        }

        interface OnAdapterListener {
            fun onItemClickListener( item: UserResponse)
        }

    }

}