package com.nextlevelprogrammers.newscafe

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity(), itemClick {
    private lateinit var mAuth:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        mAuth= Adapter(this)
        recyclerView.adapter=mAuth
        fetch()
    }

    fun fetch(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
            val jsonArray=response.getJSONArray("articles")
                val newslist:ArrayList<Data> = ArrayList()
                for (i in 0 until jsonArray.length() ){
                    val jsonObjectRequest=jsonArray.getJSONObject(i)
                    val news=Data(
                        jsonObjectRequest.getString("urlToImage"),
                        jsonObjectRequest.getString("url"),
                        jsonObjectRequest.getString("title"),
                        jsonObjectRequest.getString("author")
                    )
                    newslist.add(news)
                }
                mAuth.update(newslist)
            },
            { error ->

            })
        queue.add(request)
    }
    fun itemClick(url:String){

    }

    override fun onClick(items: Data) {
        val url = items.url
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(this@MainActivity, Uri.parse(url))

    }
}