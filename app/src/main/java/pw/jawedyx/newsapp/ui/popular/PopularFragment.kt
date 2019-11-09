package pw.jawedyx.newsapp.ui.popular


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import pw.jawedyx.newsapp.R
import pw.jawedyx.newsapp.ui.popular.vm.PopularListVM

class PopularFragment : Fragment() {

    private val vm: PopularListVM by viewModel()

    private lateinit var adapter: PopularAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PopularAdapter(viewLifecycleOwner, vm.content) { storyModel ->
            Log.d("click", "$storyModel has been clicked")
        }
        recyclerView = view.findViewById<RecyclerView>(R.id.list).also {
            it.adapter = adapter
        }
        vm.toasts.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })
    }
}
