package pw.jawedyx.newsapp.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pw.jawedyx.newsapp.data.popular.PopularApiService
import pw.jawedyx.newsapp.data.popular.mapper.PopularMapper
import pw.jawedyx.newsapp.data.popular.repository.PopularRepository
import pw.jawedyx.newsapp.domain.popular.PopularInteractor
import pw.jawedyx.newsapp.ui.vm.PopularListVM
import pw.jawedyx.newsapp.utils.ResourceProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.nytimes.com"

private val baseModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

private val listModule = module {
    single { providePopularApi(get()) }
    single { ResourceProvider(androidApplication().applicationContext) }

    factory { PopularRepository(get()) }
    factory { PopularMapper() }
    factory { PopularInteractor(get(), get()) }
    viewModel { PopularListVM(get(), get()) }
}

fun providePopularApi(retrofit: Retrofit): PopularApiService = retrofit.create(
    PopularApiService::class.java
)

val koinModules = listOf(baseModule, listModule)