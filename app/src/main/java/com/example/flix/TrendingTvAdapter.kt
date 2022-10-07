package com.example.flix

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
const val TRENDING_EXTRA = "TRENDING_EXTRA"
private const val TAG = "TrendingTvAdapter"
class TrendingTvAdapter(private val context: Context, private val trending: List<TrendingTv>):
    RecyclerView.Adapter<TrendingTvAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tv_list_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trendingMovies = trending[position]
        holder.bind(trendingMovies)
    }

    override fun getItemCount() = trending.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val trendingImageView =  itemView.findViewById<ImageView>(R.id.ivTrendingTv) //mView.findViewById<View>(R.id.tvTrendingName) as TextView
         private val trendingMovieNameTextView = itemView.findViewById<TextView>(R.id.tvTrendingName)
         private val airDateTextView = itemView.findViewById<TextView>(R.id.tvAirDate)

        init {
            itemView.setOnClickListener(this)
        }

        //set up the onBindViewHolder method
        fun bind(trendingTv: TrendingTv) {
            trendingMovieNameTextView.text = trendingTv?.title
            airDateTextView.text  = trendingTv.firstAirDate
            Glide.with(context)
                .load(trendingTv.trendingImageUrl)
                .centerInside()
                .into(trendingImageView)
        }

        override fun onClick(v: View?) {
            val trend = trending[absoluteAdapterPosition]
            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TRENDING_EXTRA, trend)
            context.startActivity(intent)
        }


    }
}