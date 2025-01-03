package app.c14210290.myrestoapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.OrderDetailEntity

class adapter_showPesananDanJumlah(private val dataDetail: MutableList<OrderDetailEntity>):

    RecyclerView.Adapter<adapter_showPesananDanJumlah.daftarOrderDetailsViewHolder>(){
        inner class daftarOrderDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvNamaMakanan = itemView.findViewById<TextView>(R.id.tv_OrderDetails_itemName)
            val tvQuantityMakanan = itemView.findViewById<TextView>(R.id.tv_OrderDetails_itemQuantity)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): daftarOrderDetailsViewHolder {
            val view: View = View.inflate(parent.context, R.layout.item_show_order_details, null)
            return daftarOrderDetailsViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dataDetail.size
        }

        override fun onBindViewHolder(holder: adapter_showPesananDanJumlah.daftarOrderDetailsViewHolder, position: Int) {
            var daftar = dataDetail[position]
            holder.tvNamaMakanan.setText(daftar.menuItemId)
            holder.tvQuantityMakanan.setText(daftar.quantity)
        }

        fun isiData(daftar: MutableList<OrderDetailEntity>){
            dataDetail.clear()
            dataDetail.addAll(daftar)
            notifyDataSetChanged()
        }

    }