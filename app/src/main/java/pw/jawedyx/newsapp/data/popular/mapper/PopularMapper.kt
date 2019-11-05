package pw.jawedyx.newsapp.data.popular.mapper

import pw.jawedyx.newsapp.data.BaseMapper
import pw.jawedyx.newsapp.data.popular.StoryModel
import pw.jawedyx.newsapp.data.popular.pojo.PopularResponse
import pw.jawedyx.newsapp.data.popular.pojo.map

class PopularMapper : BaseMapper<PopularResponse, List<StoryModel>> {

    override fun applySuccess(response: PopularResponse): List<StoryModel> = response.results?.map {
        it.map()
    } ?: emptyList()

}