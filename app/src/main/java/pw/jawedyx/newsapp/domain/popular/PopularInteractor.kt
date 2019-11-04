package pw.jawedyx.newsapp.domain.popular

import pw.jawedyx.newsapp.data.Params
import pw.jawedyx.newsapp.data.Result
import pw.jawedyx.newsapp.data.popular.StoryModel
import pw.jawedyx.newsapp.data.popular.mapper.PopularMapper
import pw.jawedyx.newsapp.data.popular.repository.PopularRepository
import pw.jawedyx.newsapp.domain.base.BaseInteractor

class PopularInteractor(
    private val repository: PopularRepository,
    private val mapper: PopularMapper
) : BaseInteractor<List<StoryModel>, PopularInteractor.PopularParams>() {

    override fun execute(params: PopularParams): Result<List<StoryModel>> = try {

        Result.Success(
            mapper.applySuccess(
                repository.getPopularArticles(params.theme)
            )
        )
    } catch (e: Exception) {
        Result.Error(e)
    }

    data class PopularParams(val theme: String = "science") : Params
}

