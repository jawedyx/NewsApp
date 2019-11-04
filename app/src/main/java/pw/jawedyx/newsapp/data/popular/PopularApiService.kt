package pw.jawedyx.newsapp.data.popular

import pw.jawedyx.newsapp.BuildConfig
import pw.jawedyx.newsapp.data.popular.pojo.PopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PopularApiService {
    @GET("/svc/topstories/v2/{theme}.json?api-key=${BuildConfig.API_KEY}")
    fun getPopular(@Path("theme") theme: String): Call<PopularResponse>
}