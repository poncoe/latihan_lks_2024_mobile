package id.telesandi.lks.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import id.telesandi.lks.InvoiceActivity
import id.telesandi.lks.MainViewModel
import id.telesandi.lks.R
import id.telesandi.lks.adapter.MainAdapter
import id.telesandi.lks.databinding.FragmentHomeBinding
import id.telesandi.lks.model.Produk
import id.telesandi.lks.network.ProdukApi

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var myAdapter: MainAdapter

    private var totalQuantity = 0
    private var totalHarga = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = MainAdapter(::onQuantityChange)

        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        binding.btnBayar.setOnClickListener {
            val intent = Intent(context, InvoiceActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("totalharga", totalHarga)
            context?.startActivity(intent)
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun onQuantityChange(quantity: Int) {
        totalQuantity += quantity
        updateQuantity()

    }

    private fun updateQuantity() {
        binding.btnBayar.text = "Bayar Sekarang ($totalQuantity)"
        totalHarga = "($totalQuantity)"
    }

    private fun updateProgress(status: ProdukApi.ApiStatus) {
        when (status) {
            ProdukApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnBayar.visibility = View.GONE
            }
            ProdukApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                binding.btnBayar.visibility = View.VISIBLE
            }
            ProdukApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.btnBayar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}