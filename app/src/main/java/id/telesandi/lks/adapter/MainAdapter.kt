package id.telesandi.lks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.telesandi.lks.databinding.ListItemBinding
import id.telesandi.lks.model.RPL

class MainAdapter(private val data: List<RPL>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rpl: RPL) = with(binding) {
            image.setImageResource(rpl.gambar)
            txtNama.text = rpl.nama
            txtKelas.text = rpl.kelas

            root.setOnClickListener {
                Toast.makeText(root.context, "Kamu Mengklik " + rpl.nama, Toast.LENGTH_LONG).show()
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
        holder.bind(data[position])
    }

}