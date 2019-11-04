package pw.jawedyx.newsapp.data.popular.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Ответ сервера на запрос популярных статей
 */
data class PopularResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null,
    @SerializedName("section")
    @Expose
    var section: String? = null,
    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String? = null,
    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null,
    @SerializedName("results")
    @Expose
    var results: List<TopStory>? = null
)