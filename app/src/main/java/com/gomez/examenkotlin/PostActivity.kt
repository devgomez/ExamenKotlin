package com.gomez.examenkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomez.examenkotlin.adapters.PostAdapter
import com.gomez.examenkotlin.models.PostResponse
import com.gomez.examenkotlin.services.ApiRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PostActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        adapter = PostAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        postRecyclerView.layoutManager= linearLayoutManager
        postRecyclerView.adapter = adapter

        callService()
    }

    private fun callService() {
        val service = ApiRepository.RetrofitRepository.getService()

        GlobalScope.launch(Dispatchers.IO) {
            val response =  service.getPosts()

            withContext(Dispatchers.Main) {

                try {
                    if(response.isSuccessful) {
                        val posts : List<PostResponse>?  = response.body()
                        if( posts != null) updateInfo(posts)
                    }else{
                        Toast.makeText(this@PostActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e : HttpException) {
                    Toast.makeText(this@PostActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateInfo(list: List<PostResponse>) {
        adapter.updateList(list)
    }

    fun onItemClickListener(item: PostResponse) {
        val postString : String = Gson().toJson(item, PostResponse::class.java)
        Log.d("GSON", postString )
        val intent = Intent(this, CommentActivity::class.java)
         intent.putExtra("post", postString)
        startActivity(intent)

    }
}