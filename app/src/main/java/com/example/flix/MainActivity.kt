package com.example.flix

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flix.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Headers
import okhttp3.Response
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}
private const val TAG = "MainActivity/"
private const val SEARCH_API_KEY = "748f1b3dbb2a932ecb10d5cd907947e3"
private const val TRENDING_MOVIES = "https://api.themoviedb.org/3/trending/tv/day?api_key=748f1b3dbb2a932ecb10d5cd907947e3"

class MainActivity : AppCompatActivity() {
    private val trending = mutableListOf<TrendingTv>()
    private lateinit var trendingRv: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        trendingRv = findViewById(R.id.trending)

        val trAdapter = TrendingTvAdapter(this, trending)
        trendingRv.adapter = trAdapter

        trendingRv.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            trendingRv.addItemDecoration(dividerItemDecoration)
        }

        trendingRv.layoutManager = GridLayoutManager(this, 1)

        val progressBar = view.findViewById<View>(R.id.progress) as ProgressBar
        progressBar.isVisible = true
        val client = AsyncHttpClient()
        client.get(TRENDING_MOVIES, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
                progressBar.isVisible = true
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                progressBar.isVisible = false
                try {
                    val parsedJson = createJson().decodeFromString(
                        BaseResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.result?.let { list ->
                        trending.addAll(list)
                    }

                    parsedJson.result?.let { list ->
                            trending.addAll(list)
                        trAdapter?.notifyDataSetChanged()
                    }


                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }


            }
        })


    }
}

// trendingRv.adapter?.notifyDataSetChanged()