package pw.jawedyx.newsapp.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pw.jawedyx.newsapp.data.Params
import pw.jawedyx.newsapp.data.Result

/**
 * Базовый интерактор
 *
 * Примечание: тут лучше подходит abstract, чем interface,
 * но чтобы забиндить реализацию пришлось сделать интерфейс.
 */
interface BaseInteractor<RESPONSE : Any, PARAMS : Params> {

    fun execute(params: PARAMS): Result<RESPONSE>

    /**
     * Выполнить в фоне и вернуть результат в [resultAction]
     *
     * @param scope вьюмодели
     * @param params аргументы для репозитория
     */
    fun inBackground(
        scope: CoroutineScope,
        params: PARAMS,
        resultAction: (Result<RESPONSE>) -> Unit = {}
    ) {
        val data = scope.async(Dispatchers.IO) { execute(params) }
        scope.launch { resultAction(data.await()) }
    }

}