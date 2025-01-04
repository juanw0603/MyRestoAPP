package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class konfirmasiBayarPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_bayar_page)

        // Ambil data dari Intent
        val orderList = intent.getStringArrayListExtra("orderList") ?: arrayListOf()
        val subtotal = intent.getDoubleExtra("subtotal", 0.0)

        // Atur RecyclerView untuk menampilkan orderList
        val recyclerView = findViewById<RecyclerView>(R.id.rvHasilOrder)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrderAdapter(orderList)

        // Tampilkan data summary
        findViewById<TextView>(R.id.tvSubtotal).text = "Subtotal: Rp${subtotal.toInt()}"

        val serviceCharge = subtotal * 0.1
        val tax = subtotal * 0.11
        val total = subtotal + serviceCharge + tax

        findViewById<TextView>(R.id.tvServiceCharge).text = "Service (10%): Rp${serviceCharge.toInt()}"
        findViewById<TextView>(R.id.tvTax).text = "Pajak (11%): Rp${tax.toInt()}"
        findViewById<TextView>(R.id.tvTotalAmount).text = "Total: Rp${total.toInt()}"

        // Spinner metode pembayaran
        val paymentMethods = listOf("Cash", "Debit Card", "QRis")
        val spinner = findViewById<Spinner>(R.id.paymentMethodDropdown)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, paymentMethods)
        spinner.adapter = adapter

        // Tombol konfirmasi pembayaran
        findViewById<Button>(R.id.buttonConfirm).setOnClickListener {
            val selectedMethod = spinner.selectedItem.toString()

            // Navigasi ke halaman berikutnya
            val intent = Intent(this, payDone::class.java)
            intent.putExtra("paymentMethod", selectedMethod)
            intent.putExtra("totalAmount", total)
            startActivity(intent)
        }
    }
}