package id.telesandi.lks

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.telesandi.lks.databinding.ActivityInvoiceBinding

class InvoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mengaktifkan navUp / tombol kembali
        val ab: ActionBar? = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        // rubah title activity
        title = "Invoice"

        // ambil data dari intent
        val i = this.intent

        // menerima data dari intent
        val totalHarga = i.extras!!.getString("totalharga")
        binding.txtTotalHarga.text = "Rp. $totalHarga"

        binding.btnSelesai.setOnClickListener {
            this.finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

}