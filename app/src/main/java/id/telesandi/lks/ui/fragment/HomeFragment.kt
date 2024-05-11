package id.telesandi.lks.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import id.telesandi.lks.R
import id.telesandi.lks.adapter.MainAdapter
import id.telesandi.lks.databinding.FragmentHomeBinding
import id.telesandi.lks.model.RPL

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }
    }

    private fun getData(): List<RPL> {
        return listOf(
            RPL(R.drawable.coedotz, "Nopal", "RPL-4"),
            RPL(R.drawable.coedotz, "Coedotz", "RPL-2"),
        )
    }
}