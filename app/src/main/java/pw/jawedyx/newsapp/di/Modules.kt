package pw.jawedyx.newsapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import pw.jawedyx.newsapp.data.BaseMapper
import pw.jawedyx.newsapp.data.popular.PopularApiService
import pw.jawedyx.newsapp.data.popular.mapper.PopularMapper
import pw.jawedyx.newsapp.data.popular.repository.PopularRepository
import pw.jawedyx.newsapp.domain.base.BaseInteractor
import pw.jawedyx.newsapp.domain.popular.PopularInteractor
import pw.jawedyx.newsapp.ui.popular.vm.PopularListVM
import pw.jawedyx.newsapp.utils.ResourceProvider
import pw.jawedyx.newsapp.utils.ResourceProviderInterface
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
    single { ResourceProvider(get()) } bind ResourceProviderInterface::class
}

private val popularModule = module {
    single { get<Retrofit>().create(PopularApiService::class.java) }

    factory { PopularRepository(get()) }
    factory { PopularMapper() } bind BaseMapper::class
    factory { PopularInteractor(get(), get()) } bind BaseInteractor::class
    viewModel { PopularListVM(get(), get()) }
}

val koinModules = listOf(baseModule, popularModule)