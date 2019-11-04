package pw.jawedyx.newsapp.data

abstract class BaseMapper<API_RESPONSE_POJO, MODEL> {
    abstract fun applySuccess(response: API_RESPONSE_POJO): MODEL
}