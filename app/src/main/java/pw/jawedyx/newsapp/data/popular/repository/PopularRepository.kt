package pw.jawedyx.newsapp.data.popular.repository

import pw.jawedyx.newsapp.data.BaseRepository
import pw.jawedyx.newsapp.data.popular.PopularApiService
import pw.jawedyx.newsapp.data.popular.PopularError
import pw.jawedyx.newsapp.data.popular.PopularError.Companion.NO_API_KEY_CODE
import pw.jawedyx.newsapp.data.popular.PopularError.Companion.NO_QUOTAS_CODE
import pw.jawedyx.newsapp.data.popular.pojo.PopularResponse


class PopularRepository(private val api: PopularApiService) :
    BaseRepository {

    @Throws(Exception::class)
    fun getPopularArticles(theme: String): PopularResponse = api.getPopular(theme).execute().run {
        if (isSuccessful) {
            body() ?: PopularResponse()
        } else {
            when (this.code()) {
                NO_QUOTAS_CODE -> throw PopularError.getTooManyRequestsError()
                NO_API_KEY_CODE -> throw PopularError.getCheckYourApiKeyError()
                else -> throw Exception(message())
            }
        }
    }
}