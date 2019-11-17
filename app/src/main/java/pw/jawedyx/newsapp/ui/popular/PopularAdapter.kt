package pw.jawedyx.newsapp.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    private var items: List<StoryModel> = emptyList(),
    private val clickAction: (StoryModel) -> Unit
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_popular,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateContent(newContent: List<StoryModel>) {
        items = newContent
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemHolder: View) : RecyclerView.ViewHolder(itemHolder) {
        val title = itemHolder.findViewById<TextView>(R.id.title)

        fun bind(item: StoryModel) {
            title.text = item.title
            itemHolder.setOnClickListener { clickAction(item) }
        }
    }
}