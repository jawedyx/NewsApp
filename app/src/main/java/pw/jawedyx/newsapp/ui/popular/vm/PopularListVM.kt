package pw.jawedyx.newsapp.ui.popular.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pw.jawedyx.newsapp.data.Result
import pw.jawedyx.newsapp.data.popular.PopularError
import pw.jawedyx.newsapp.data.popular.StoryModel
import pw.jawedyx.newsapp.domain.base.BaseInteractor
import pw.jawedyx.newsapp.domain.popular.PopularInteractor
import pw.jawedyx.newsapp.utils.ResourceProviderInterface

class PopularListVM(
    private val interactor: BaseInteractor<List<StoryModel>, PopularInteractor.PopularParams>,
    private val resProvider: ResourceProviderInterface
) : ViewModel() {

    init {
        doRequest()
    }

    private val newsLiveData = MutableLiveData<List<StoryModel>>()
    private val toastLiveData = MutableLiveData<String>()

    private fun doRequest() {
        val params = PopularInteractor.PopularParams("science")
        interactor.inBackground(viewModelScope, params) {
            when (it) {
                is Result.Success -> newsLiveData.value = it.data
                is Result.Error -> toastLiveData.value = when (it.exception) {
                    is PopularError -> resProvider.getString(it.exception.userMessageRes)
                    else -> it.exception.message
                }
            }
        }
    }

    val toasts: LiveData<String> = toastLiveData
    val content: LiveData<List<StoryModel>> = newsLiveData
}