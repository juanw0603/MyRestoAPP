package app.c14210290.myrestoapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.adapter_OwnerDaftarWaiterOrCashier.OnItemClickCallback
import app.c14210290.myrestoapp.database.CashierOrWaiterEntity
import app.c14210290.myrestoapp.database.TableEntity

class adapter_kasirMeja(private val dataMeja: MutableList<TableEntity>) :

    RecyclerView.Adapter<adapter_kasirMeja.daftarMejaListViewHolder>() {
    inner class daftarMejaListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNoMeja = itemView.findViewById<TextView>(R.id.tableNo)
        val tvStatusMeja = itemView.findViewById<TextView>(R.id.tableStatus)
        val btn_getMejaInfo = itemView.findViewById<CardView>(R.id.card_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): daftarMejaListViewHolder {
        val view: View = View.inflate(parent.context, R.layout.item_nomor_meja, null)
        return daftarMejaListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataMeja.size
    }

    override fun onBindViewHolder(holder: adapter_kasirMeja.daftarMejaListViewHolder, position: Int) {
        var daftar = dataMeja[position]
        holder.tvNoMeja.setText(daftar.number.toString())
        holder.tvStatusMeja.setText(daftar.status)


        holder.btn_getMejaInfo.setOnClickListener{
            onItemClickCallback.getId(daftar)
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun getId(daftarMeja: TableEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun isiData(daftar: MutableList<TableEntity>){
        dataMeja.clear()
        dataMeja.addAll(daftar)
        notifyDataSetChanged()
    }
}

