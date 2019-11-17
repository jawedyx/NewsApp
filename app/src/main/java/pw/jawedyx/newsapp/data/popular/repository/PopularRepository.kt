package pw.jawedyx.newsapp.data.popular.repository

import android.util.Log
import pw.jawedyx.newsapp.data.BaseRepository
import pw.jawedyx.newsapp.data.popular.PopularApiService
import pw.jawedyx.newsapp.data.popular.PopularError
import pw.jawedyx.newsapp.data.popular.PopularError.Companion.NO_API_KEY_CODE
import pw.jawedyx.newsapp.data.popular.PopularError.Companion.NO_QUOTAS_CODE
import pw.jawedyx.newsapp.data.popular.pojo.PopularResponse


class PopularRepository(private val api: PopularApiService) : BaseRepository {

    @Throws(Exception::class)
    fun getPopularArticles(theme: String): PopularResponse = api.getPopular(theme).execute().run {
        if (isSuccessful) {
            body().also {
                Log.d(PopularRepository::class.java.simpleName, it.toString())
            } ?: PopularResponse()
        } else {
            Log.e(className, "Error: code ${code()}, \n ${errorBody()}")
            when (code()) {
                NO_QUOTAS_CODE -> throw PopularError.getTooManyRequestsError()
                NO_API_KEY_CODE -> throw PopularError.getCheckYourApiKeyError()
                else -> throw Exception(message())
            }
        }
    }

    override val className: String = PopularRepository::class.java.simpleName
}