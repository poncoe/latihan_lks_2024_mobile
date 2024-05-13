package id.telesandi.lks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.telesandi.lks.R
import id.telesandi.lks.databinding.ListItemBinding
import id.telesandi.lks.model.Produk
import id.telesandi.lks.network.ProdukApi

class MainAdapter(private val onQuantityChangeListener: (Int) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<Produk>()
    fun updateData(newData: List<Produk>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var quantity = 0

        fun bind(produk: Produk, onQuantityChangeListener: (Int) -> Unit) = with(binding) {
            Glide.with(image.context)
                .load(ProdukApi.getProdukUrl(produk.gambar))
                .error(R.drawable.coedotz)
                .into(image)

            txtNama.text = produk.nama
            txtKelas.text = "Rp. " + produk.harga.toString()
            txtRating.text = produk.rating

            btnCart.setOnClickListener {
                Toast.makeText(root.context, "Tombol Cart di klik", Toast.LENGTH_LONG).show()
            }

            btnMinus.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                    val totalharga = produk.harga * quantity--
                    txtQuantity.text = quantity.toString()
                    onQuantityChangeListener(totalharga)
                }
            }

            btnPlus.setOnClickListener {
                quantity++
                val totalharga = produk.harga * quantity++
                txtQuantity.text = quantity.toString()
                onQuantityChangeListener(totalharga)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], onQuantityChangeListener)
    }

}