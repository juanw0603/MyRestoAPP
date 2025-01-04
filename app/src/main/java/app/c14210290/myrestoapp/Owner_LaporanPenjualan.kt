package app.c14210290.myrestoapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import app.c14210290.myrestoapp.database.RestoDB
import kotlinx.coroutines.launch
class Owner_LaporanPenjualan : Fragment() {

    private lateinit var DB: RestoDB
    private lateinit var textViewPendapatan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi database
        DB = RestoDB.getdatabase(requireContext())
        if (DB.pendapatanDao().getTotalPendapatan() == null) {
            DB.pendapatanDao().insertPendapatanDummy()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment
        val view = inflater.inflate(R.layout.fragment_owner__laporan_penjualan, container, false)

        // Inisialisasi elemen UI
        textViewPendapatan = view.findViewById(R.id.textViewPendapatan)

        // Tampilkan data pendapatan
        loadTotalPendapatan()

        return view
    }

    private fun loadTotalPendapatan() {
        lifecycleScope.launch {
            val totalPendapatan = DB.pendapatanDao().getTotalPendapatan() ?: 0
            textViewPendapatan.text = "Rp. $totalPendapatan"
        }
    }

    // Companion object untuk membuat instance fragment tanpa argumen
    companion object {
        @JvmStatic
        fun newInstance() = Owner_LaporanPenjualan()
    }
}