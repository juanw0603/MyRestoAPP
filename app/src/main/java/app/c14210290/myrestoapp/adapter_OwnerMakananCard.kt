package app.c14210290.myrestoapp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.MenuItemEntity

class adapter_OwnerMakananCard(private val dataMakanan: MutableList<MenuItemEntity>):

    RecyclerView.Adapter<adapter_OwnerMakananCard.ListViewHolder>() {
        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvNamaMakanan = itemView.findViewById<TextView>(R.id.tvNamaMakanan)
            var tvHargaMakanan = itemView.findViewById<TextView>(R.id.tvHargaMakanan)

            var btn_editMakanan = itemView.findViewById<TextView>(R.id.btn_editMakanan)
            var btn_deleteMakanan = itemView.findViewById<TextView>(R.id.btn_deleteMakanan)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view: View = View.inflate(parent.context, R.layout.owner_makanan_card, null)
            return ListViewHolder(view)
        }

    override fun getItemCount(): Int {
        return dataMakanan.size
    }

    override fun onBindViewHolder(holder: adapter_OwnerMakananCard.ListViewHolder, position: Int) {
            var daftar = dataMakanan[position]
            holder.tvNamaMakanan.setText(daftar.name)
            holder.tvHargaMakanan.setText(daftar.price.toString())


            holder.btn_editMakanan.setOnClickListener{
                val intent = Intent(holder.itemView.context, activityAddOrEditMakanan::class.java).apply {
                    putExtra("edit", 1)
                    putExtra("menuItemsid", daftar.menuItemsId)
                    putExtra("MenuName", daftar.name)
                    putExtra("MenuPrice", daftar.price.toString())
                }

                it.context.startActivity(intent)
            }

            holder.btn_deleteMakanan.setOnClickListener{
                onItemClickCallback.delDataMenu(daftar)
            }


        }
        private lateinit var onItemClickCallback: OnItemClickCallback


        interface OnItemClickCallback{
            fun delDataMenu(dataMenu: MenuItemEntity)
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