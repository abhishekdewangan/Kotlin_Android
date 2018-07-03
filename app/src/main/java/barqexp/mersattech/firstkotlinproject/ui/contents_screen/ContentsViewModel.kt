package barqexp.mersattech.firstkotlinproject.ui.contents_screen

import android.app.Application
import android.arch.lifecycle.*
import barqexp.mersattech.firstkotlinproject.data.Content
import barqexp.mersattech.firstkotlinproject.data.Contents
import barqexp.mersattech.firstkotlinproject.network.RetrofitService
import barqexp.mersattech.firstkotlinproject.utils.Keys
import kotlinx.coroutines.experimental.launch

class ContentsViewModel(application: Application, val contentType: String, val contentFilterType: String) : AndroidViewModel(application) {
    private var contentsLiveData: MutableLiveData<ArrayList<Content>> = MutableLiveData()
    private var page: Int = 0
    val isLoadingLiveData = MutableLiveData<Int>()
    init {
        contentsLiveData.value = arrayListOf()
    }

    private suspend fun fetchContents() {
        updateLoadingStatus(true)
        val contents: Contents = RetrofitService.create().getMovies(
                Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN, page).await()
        if (null != contentsLiveData.value) {
            contentsLiveData.value!!.addAll(contents.results)
            contentsLiveData.postValue(contentsLiveData.value)
        }
        updateLoadingStatus(false)
    }

    private fun updateLoadingStatus(loadingStatus: Boolean) {
        isLoadingLiveData.postValue(when (page) {
            1 -> if (loadingStatus) Keys.LOADING_FRESH else Keys.STOP_LOADING
            else -> if (loadingStatus) Keys.LOADING_NEXT else Keys.STOP_PAGINATION
        })
    }

    fun getContents(isRefresh: Boolean): LiveData<ArrayList<Content>> {
        page = when (isRefresh) {
            true -> 1
            else -> page + 1
        }
        launch {
            fetchContents()
        }
        return contentsLiveData;
    }

    class Factory(private val mApplication: Application, private val contentType: String,
                  private val contentFilterType: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ContentsViewModel(mApplication, contentType, contentFilterType) as T
        }
    }
}