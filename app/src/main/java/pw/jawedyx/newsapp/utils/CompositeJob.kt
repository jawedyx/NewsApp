package pw.jawedyx.newsapp.utils

import kotlinx.coroutines.Job

class CompositeJob {

    private val map = hashMapOf<String, Job>()

    fun add(job: Job, key: String = job.hashCode().toString())
            = map.put(key, job)?.cancel()

    fun cancel(key: String) = map[key]?.cancel()

    fun cancel() = map.values.forEach { u -> u.cancel() }
}