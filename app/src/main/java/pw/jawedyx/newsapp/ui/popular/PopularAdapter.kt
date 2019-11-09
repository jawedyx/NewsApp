package pw.jawedyx.newsapp.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import pw.jawedyx.newsapp.R
import pw.jawedyx.newsapp.data.popular.StoryModel

/**
 * Адаптер главной страницы, содержащий популярные статьи
 *
 * @param fragmentLifecycleOwner активности или фрагмента для подписки на события
 * @param items список данных
 * @param clickAction действие по клику на элемент списка
 */
class PopularAdapter(
    fragmentLifecycleOwner: LifecycleOwner,
    private var items: LiveData<List<StoryModel>>,
    private val clickAction: (StoryModel) -> Unit
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    init {
        items.observe(fragmentLifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_popular,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = items.value?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items.value?.let {
            holder.bind(it[position])
        }
    }

    inner class ViewHolder(private val itemHolder: View) : RecyclerView.ViewHolder(itemHolder) {
        val title = itemHolder.findViewById<TextView>(R.id.title)

        fun bind(item: StoryModel) {
            title.text = item.title
            itemHolder.setOnClickListener { clickAction(item) }
        }
    }
}