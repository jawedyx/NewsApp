package pw.jawedyx.newsapp.data.popular

import androidx.annotation.StringRes
import pw.jawedyx.newsapp.R
import java.lang.Exception

data class PopularError(@StringRes val userMessageRes: Int, val code: Int) : Exception() {
    companion object {
        const val NO_QUOTAS_CODE = 429
        const val NO_API_KEY_CODE = 401

        fun getTooManyRequestsError() = PopularError(R.string.popular_no_quotas, NO_QUOTAS_CODE)
        fun getCheckYourApiKeyError() = PopularError(R.string.popular_no_api_key, NO_API_KEY_CODE)
    }
}