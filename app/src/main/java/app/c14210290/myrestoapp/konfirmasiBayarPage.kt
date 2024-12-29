package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class konfirmasiBayarPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konfirmasi_bayar_page)

        // Sample data
        val daftarBayar = mutableListOf(
            DaftarBayar("Nasi Goreng", 2, 25000.0),
            DaftarBayar("Mie Ayam", 1, 20000.0),
            DaftarBayar("Es Teh", 3, 5000.0)
        )
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)
        buttonConfirm.setOnClickListener {
            // Menavigasi ke payDone.kt
            val intent = Intent(this, payDone::class.java)
            startActivity(intent)
        }
        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvHasilOrder)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterBayar(daftarBayar)

        // Calculate totals
        val subtotal = daftarBayar.sumOf { it.quantity * it.price }
        val serviceCharge = subtotal * 0.1
        val tax = subtotal * 0.11
        val total = subtotal + serviceCharge + tax

        // Update summary views
        findViewById<TextView>(R.id.tvSubtotal).text = "Subtotal: Rp${subtotal.toInt()}"
        findViewById<TextView>(R.id.tvServiceCharge).text =
            "Service (10%): Rp${serviceCharge.toInt()}"
        findViewById<TextView>(R.id.tvTax).text = "Pajak (11%): Rp${tax.toInt()}"
        findViewById<TextView>(R.id.tvTotalAmount).text = "Total: Rp${total.toInt()}"

        // Setup Spinner for payment methods
        val paymentMethods = listOf("Cash", "Debit Card", "QRis")
        val spinner = findViewById<Spinner>(R.id.paymentMethodDropdown)
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, paymentMethods)
        spinner.adapter = adapter

        // Confirm button action
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            val selectedMethod = spinner.selectedItem.toString()
            // Handle confirmation logic here
        }
    }

    // Data class for RecyclerView items
    data class DaftarBayar(val menuName: String, val quantity: Int, val price: Double)

    // RecyclerView Adapter
    class AdapterBayar(private val daftarBayar: MutableList<DaftarBayar>) :
        RecyclerView.Adapter<AdapterBayar.ListViewHolder>() {

        class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvMenuName: TextView = itemView.findViewById(R.id.tvMenuName)
            val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
            val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bayar, parent, false)
            return ListViewHolder(view)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val item = daftarBayar[position]
            holder.tvMenuName.text = item.menuName
            holder.tvQuantity.text = "Qty: ${item.quantity}"
            holder.tvPrice.text = "Rp${(item.price * item.quantity).toInt()}"
        }

        override fun getItemCount(): Int = daftarBayar.size
    }
}
