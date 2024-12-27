package app.c14210290.myrestoapp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.adapter_OwnerMakananCard.ListViewHolder
import app.c14210290.myrestoapp.database.CashierOrWaiterEntity
import app.c14210290.myrestoapp.database.MenuItemEntity

class adapter_OwnerDaftarWaiterOrCashier(private val dataKaryawan: MutableList<CashierOrWaiterEntity>) :

    RecyclerView.Adapter<adapter_OwnerDaftarWaiterOrCashier.daftarKaryawanListViewHolder>() {
    inner class daftarKaryawanListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaKaryawan = itemView.findViewById<TextView>(R.id.tvwaiterOrCashierName)
        var tvEmailKaryawan = itemView.findViewById<TextView>(R.id.tvwaiterOrCashierEmail)

        val btn_editKaryawan = itemView.findViewById<TextView>(R.id.btn_editUserWaiterOrCashier)
        val btn_deleteKaryawan = itemView.findViewById<TextView>(R.id.btn_deleteWaiterOrCashier)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): daftarKaryawanListViewHolder {
        val view: View = View.inflate(parent.context, R.layout.user_waiter_or_cashier_card, null)
        return daftarKaryawanListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataKaryawan.size
    }

    override fun onBindViewHolder(holder: daftarKaryawanListViewHolder, position: Int) {
        var daftar = dataKaryawan[position]
        holder.tvNamaKaryawan.setText(daftar.name)
        holder.tvEmailKaryawan.setText(daftar.email)


        holder.btn_editKaryawan.setOnClickListener{
            val intent = Intent(holder.itemView.context, addOrEditCashierOrWaiter::class.java).apply {
                putExtra("edit",1 )
                putExtra("id", daftar.cashiersOrWaiterId)
                putExtra("nama", daftar.name)
                putExtra("username", daftar.username)
                putExtra("email", daftar.email)
                putExtra("password", daftar.password)
            }

            it.context.startActivity(intent)
        }

        holder.btn_deleteKaryawan.setOnClickListener{
            onItemClickCallback
        }

    }

    private lateinit var onItemClickCallback: OnItemClickCallback


    interface OnItemClickCallback{
        fun delDataMenu(daftarKaryawan: CashierOrWaiterEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun isiData(daftar: MutableList<CashierOrWaiterEntity>){
        dataKaryawan.clear()
        dataKaryawan.addAll(daftar)
        notifyDataSetChanged()
    }
}
