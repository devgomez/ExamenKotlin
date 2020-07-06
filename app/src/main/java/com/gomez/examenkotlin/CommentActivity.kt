package com.gomez.examenkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomez.examenkotlin.adapters.CommentAdapter
import com.gomez.examenkotlin.adapters.PostAdapter
import com.gomez.examenkotlin.models.PostResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_post.*

class CommentActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val postIntent = intent.getStringExtra("post")

        val post : PostResponse = Gson().fromJson(postIntent, PostResponse::class.java)

        adapter = CommentAdapter(post.comment)
        linearLayoutManager = LinearLayoutManager(this)
        commentRecyclerView.layoutManager= linearLayoutManager
        commentRecyclerView.adapter = adapter
    }



}