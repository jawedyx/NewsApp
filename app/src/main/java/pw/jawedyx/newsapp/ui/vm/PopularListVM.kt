package pw.jawedyx.newsapp.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pw.jawedyx.newsapp.data.Result
import pw.jawedyx.newsapp.data.popular.PopularError
import pw.jawedyx.newsapp.data.popular.StoryModel
import pw.jawedyx.newsapp.utils.ResourceProvider
import pw.jawedyx.newsapp.domain.popular.PopularInteractor

class PopularListVM(
    private val interactor: PopularInteractor,
    private val resProvider: ResourceProvider
) : ViewModel() {

    private val newsLiveData = MutableLiveData<List<StoryModel>>()
    private val toastLiveData = MutableLiveData<String>()

    val toasts: LiveData<String> = toastLiveData
    val content: LiveData<List<StoryModel>> = newsLiveData

    fun doRequest() {
        val params = PopularInteractor.PopularParams("science")
        interactor.inForeground(viewModelScope, params) {
            when (it) {
                is Result.Success -> newsLiveData.value = it.data
                is Result.Error -> toastLiveData.value = when (it.exception) {
                    is PopularError -> resProvider.getString(it.exception.userMessageRes)
                    else -> it.exception.message
                }
            }
        }
    }

}