package pw.jawedyx.newsapp.utils

import android.content.Context

/**
 * Провайдер ресурсов
 *
 * Поставляет ресурсы приложения
 * @param context Контекст приложения
 */
data class ResourceProvider(private val context: Context) : ResourceProviderInterface {

    /**
     * Возвращает строку по значению ресурса
     * @param res значение строкового ресурса R.string.*
     *
     * @return значение строки из strings.xml
     */
    override fun getString(res: Int) = context.resources.getString(res)
}