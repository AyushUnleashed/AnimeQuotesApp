package com.ayushunleashed.animeQuotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList



class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter:NewsListAdapter   //lateinit for later intialisation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setting layout type to linear layout
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        // object of class NewsListAdapter to have adapter for recyclerview
        mAdapter = NewsListAdapter(this)
        //setting myAdapter to be used as adapter of recycler view
        recyclerView.adapter = mAdapter
    }

        //function created just to have string input for our recycler view
    private fun fetchData()
    {
        val myUrl = "https://animechan.vercel.app/api/quotes"

        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, myUrl, null,
            { response ->

                val quoteArray = ArrayList<Quote>()
                for( i in 0 until response.length())
                {
                    val quoteCurrentObject = response.getJSONObject(i)
                    val quoteObject = Quote(
                        quoteCurrentObject.getString("anime"),
                        quoteCurrentObject.getString("character"),
                        quoteCurrentObject.getString("quote")
                    )
                    Log.d("loadedAnime",quoteObject.animeName)
                    quoteArray.add(quoteObject)
                    mAdapter.updateNews(quoteArray)
                }
            },
            { error ->
                Log.d("LBC","Lag gaye")
            }
        )

//
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: Quote) {
        Toast.makeText(this,"Anime: ${item.animeName}",Toast.LENGTH_SHORT).show()
//        val myWebView: WebView = findViewById(R.id.webview)
        //code to display it on the app itself
//        myWebView.setWebViewClient(WebViewClient())
//        myWebView.loadUrl("")
    }

    fun reloadQuotes(view: View) {
        fetchData()
    }
}

//extra code
//val myUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=b655d6ae282441feaa2e1d5834ca2f1f"
//val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, myUrl, null,
//            { response ->
//                val newsArticleArray = response.getJSONArray("articles")
//                //creating array of News objects
//                val newsArray = ArrayList<News>()
//                for(i in 0 until newsArticleArray.length())
//                {
//                    val newsCurrentObject = newsArticleArray.getJSONObject(i)
//                    val newsObject = News(
//                        newsCurrentObject.getString("title"),
//                        newsCurrentObject.getString("author"),
//                        newsCurrentObject.getString("url"),
//                        newsCurrentObject.getString("urlToImage")
//                    )
//                    Log.d("loadedTitle",newsObject.title)
//                    newsArray.add(newsObject)
//                    mAdapter.updateNews(newsArray)
//                }
//            },
//            { error ->
//                Log.d("LBC","Lode lag gye")
//                // TODO: Handle error
//            }
//        )