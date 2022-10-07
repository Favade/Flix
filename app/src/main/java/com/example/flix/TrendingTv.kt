package com.example.flix

import android.support.annotation.Keep
import com.codepath.asynchttpclient.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class BaseResponse(
    @SerialName("results")
    val result: List<TrendingTv>?
)
@Keep
@Serializable
data class TrendingTv(
    @SerialName("name")
    val title: String?,

    @SerialName("overview")
    val overview: String?,

    @SerialName("first_air_date")
    val firstAirDate: String?,

    @SerialName("poster_path")
    val movieImg: String?,

    @SerialName("popularity")
    val popularity: Double,

) : java.io.Serializable {
    val trendingImageUrl = "https://image.tmdb.org/t/p/w500/${movieImg}"
}