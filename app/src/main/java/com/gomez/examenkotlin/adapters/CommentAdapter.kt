package com.gomez.examenkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gomez.examenkotlin.CommentActivity
import com.gomez.examenkotlin.PostActivity
import com.gomez.examenkotlin.R
import com.gomez.examenkotlin.models.Comment
import com.gomez.examenkotlin.models.PostResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_post.view.*

class CommentAdapter (private var data: List<Comment>):RecyclerView.Adapter<CommentAdapter.CommentHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val inflatedView = parent.inflate(R.layout.item_comment, false)
        return CommentAdapter.CommentHolder(inflatedView)
    }
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment: Comment = this.data[position]
        holder.itemView.tv_comment_comment.text = comment.comment

        if(!comment.user_image.isBlank()) {
            Picasso.get()
                .load(comment.user_image)
                .into(holder.itemView.iv_comment_user)
        }

    }

    class CommentHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                Toast.makeText(v.context, "Item", Toast.LENGTH_SHORT).show()
            }
        }


    }
}