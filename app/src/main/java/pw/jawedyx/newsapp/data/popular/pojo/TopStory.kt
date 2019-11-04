package pw.jawedyx.newsapp.data.popular.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pw.jawedyx.newsapp.data.popular.StoryModel

/**
 * Модель популярной истории
 */
data class TopStory(
    @SerializedName("section")
    @Expose
    var section: String? = null,
    @SerializedName("subsection")
    @Expose
    var subsection: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("abstract")
    @Expose
    var abstract: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("byline")
    @Expose
    var byline: String? = null,
    @SerializedName("item_type")
    @Expose
    var itemType: String? = null,
    @SerializedName("updated_date")
    @Expose
    var updatedDate: String? = null,
    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null,
    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null,
    @SerializedName("material_type_facet")
    @Expose
    var materialTypeFacet: String? = null,
    @SerializedName("kicker")
    @Expose
    var kicker: String? = null,
    @SerializedName("des_facet")
    @Expose
    var desFacet: List<String>? = null,
    @SerializedName("org_facet")
    @Expose
    var orgFacet: List<String>? = null,
    @SerializedName("per_facet")
    @Expose
    var perFacet: List<Any>? = null,
    @SerializedName("geo_facet")
    @Expose
    var geoFacet: List<String>? = null,
    @SerializedName("short_url")
    @Expose
    var shortUrl: String? = null
)

fun TopStory.map() = StoryModel(
    title.orEmpty(),
    subsection.orEmpty(),
    byline.orEmpty()
)

