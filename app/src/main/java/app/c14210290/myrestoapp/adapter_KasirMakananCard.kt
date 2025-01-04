package app.c14210290.myrestoapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.adapter_kasirMeja.OnItemClickCallback
import app.c14210290.myrestoapp.database.MenuItemEntity
import app.c14210290.myrestoapp.database.TableEntity

class adapter_KasirMakananCard(private val dataMakanan: MutableList<MenuItemEntity>) :
    RecyclerView.Adapter<adapter_KasirMakananCard.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaMakanan = itemView.findViewById<TextView>(R.id.tvNamaItem)
        var tvHargaMakanan = itemView.findViewById<TextView>(R.id.tvHargaItem)
        var card = itemView.findViewById<ConstraintLayout>(R.id.card_makanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = View.inflate(parent.context, R.layout.kasir_makanan_card, null)
        return ListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return dataMakanan.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var daftar = dataMakanan[position]
        holder.tvNamaMakanan.setText(daftar.name)
        holder.tvHargaMakanan.setText(daftar.price.toString())

        holder.card.setOnClickListener{
            onItemClickCallback.getMenu(daftar)
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun getMenu(daftarMeja: MenuItemEntity)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun isiData(daftar: MutableList<MenuItemEntity>){
        dataMakanan.clear()
        dataMakanan.addAll(daftar)
        notifyDataSetChanged()
    }
}
