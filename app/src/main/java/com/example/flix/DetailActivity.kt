package com.example.flix

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"
class DetailActivity: AppCompatActivity() {
    private lateinit var trendingImage: ImageView
    private lateinit var trendingName: TextView
    private lateinit var trendingOverview: TextView
    private lateinit var popularity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tv_items)

        trendingImage = findViewById(R.id.mediaImage)
        trendingName = findViewById(R.id.trendingtvName)
        trendingOverview = findViewById(R.id.tvOverview)
        popularity = findViewById(R.id.tvPopularity)

        val trendingTv = intent.getSerializableExtra(TRENDING_EXTRA) as TrendingTv

        trendingName.text = trendingTv?.title
        trendingOverview.text = trendingTv?.overview
        popularity.text = trendingTv.popularity.toString()

        Glide.with(this)
            .load(trendingTv.trendingImageUrl)
            .centerInside()
            .into(trendingImage)


    }


}
