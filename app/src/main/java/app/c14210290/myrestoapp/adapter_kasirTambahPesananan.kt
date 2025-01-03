package app.c14210290.myrestoapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.OrderDetailEntity

class adapter_kasirTambahPesananan(private val dataDetail: MutableList<OrderDetailEntity>) :

    RecyclerView.Adapter<adapter_kasirTambahPesananan.daftarOrderDetailsViewHolder>() {
        inner class daftarOrderDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvNamaMakanan = itemView.findViewById<TextView>(R.id.tv_namaItem)
            val tvQuantityMakanan = itemView.findViewById<TextView>(R.id.tv_quantity)
            val btnAddQuantity = itemView.findViewById<ImageButton>(R.id.imageButton_addQuantity)
            val btnSubtractQuantity = itemView.findViewById<ImageButton>(R.id.imageButton_subtractQuantity)
        }

        override fun getItemCount(): Int {
            return dataDetail.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): daftarOrderDetailsViewHolder {
            val view: View = View.inflate(parent.context, R.layout.item_show_order_details, null)
            return daftarOrderDetailsViewHolder(view)
        }

        override fun onBindViewHolder(holder: daftarOrderDetailsViewHolder, position: Int) {
            val daftar = dataDetail[position]
            holder.tvNamaMakanan.setText(daftar.menuItemId)
        }

}
