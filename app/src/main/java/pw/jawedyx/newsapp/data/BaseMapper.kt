package pw.jawedyx.newsapp.data

interface BaseMapper<API_RESPONSE_POJO, MODEL> {
    fun applySuccess(response: API_RESPONSE_POJO): MODEL
}