package pw.jawedyx.newsapp.utils

import android.content.Context

data class ResourceProvider(private val context: Context){

    fun getString(resId: Int) = context.resources.getString(resId)
}