package com.agneswangesha.cc.postsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
     fetchPosts()

        }
        fun fetchPosts(){
            val apiInterface =
                ApiClient.buildApiInterface(PostApiInterface::class.java)
            val request = apiInterface.fetchPosts()
            request.enqueue(object : Callback<List<Post>>{

                override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
                 if (p1.isSuccessful){
                     val posts = p1.body()
                     Toast.makeText(baseContext, "fetched ${posts!!.size} posts", Toast.LENGTH_LONG)
                         .show()
                 }
                    else{
                        Toast.makeText(baseContext, p1.errorBody()?.string(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
                 Toast.makeText(baseContext,p1.message,Toast.LENGTH_SHORT).show()
                }
            })
        }




    }
