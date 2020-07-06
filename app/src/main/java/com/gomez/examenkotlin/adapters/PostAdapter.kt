package com.gomez.examenkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gomez.examenkotlin.PostActivity
import com.gomez.examenkotlin.R
import com.gomez.examenkotlin.models.PostResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private var data: List<PostResponse>, private val listener: PostActivity):RecyclerView.Adapter<PostAdapter.PostHolder>(){

    fun updateList(postList: List<PostResponse>) {
        this.data = postList
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflatedView = parent.inflate(R.layout.item_post, false)
        return PostHolder(inflatedView)
    }
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post: PostResponse = this.data[position]
        holder.itemView.tv_feed_item_username.text = post.username
        holder.itemView.tv_feed_item_description.text = post.body
        holder.itemView.tv_feed_item_likes.text = post.likes.toString()

        if(!post.user_image.isBlank()) {
            Picasso.get()
                .load(post.user_image)
                .into(holder.itemView.iv_feed_row_profile)
        }
        if(!post.image.isBlank()) {
            Picasso.get()
                .load(post.image)
                .into(holder.itemView.iv_feed_item_main)
        }

        holder.itemView.btn_feed_item_comment.setOnClickListener{listener.onItemClickListener(post)}
    }

    class PostHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }

        interface OnAdapterListener {
            fun onItemClickListener( item: PostResponse)
        }

    }
}
