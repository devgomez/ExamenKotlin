package com.gomez.examenkotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomez.examenkotlin.adapters.FriendAdapter
import com.gomez.examenkotlin.models.PostResponse
import com.gomez.examenkotlin.models.UserResponse
import com.gomez.examenkotlin.services.ApiRepository
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_friends.*
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FriendsActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        adapter = FriendAdapter(ArrayList(), this)
        linearLayoutManager = LinearLayoutManager(this)
        friendRecyclerView.layoutManager= linearLayoutManager
        friendRecyclerView.adapter = adapter

        callServiceFriends()

    }

    private fun callServiceFriends() {
        val service = ApiRepository.RetrofitRepository.getService()

        GlobalScope.launch(Dispatchers.IO) {
            val response =  service.getUsers()

            withContext(Dispatchers.Main) {

                try {
                    if(response.isSuccessful) {
                        val users : List<UserResponse>?  = response.body()
                        if( users != null) updateInfo(users)
                    }else{
                        Toast.makeText(this@FriendsActivity, "Error ${response.code()}", Toast.LENGTH_LONG).show()
                    }
                }catch (e : HttpException) {
                    Toast.makeText(this@FriendsActivity, "Error ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateInfo(list: List<UserResponse>) {
        adapter.updateList(list)
    }

    fun onItemClickListener(item: UserResponse) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)

        }
        else{
            call(item.phone)
        }

    }

    fun call(phone : String){
        val intent = Intent( Intent.ACTION_CALL)
        intent.data = Uri.parse("telf:" + phone)

        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1) call("15454566")
    }



}