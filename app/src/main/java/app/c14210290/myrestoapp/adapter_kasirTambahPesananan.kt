package app.c14210290.myrestoapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.MenuItemEntity
import app.c14210290.myrestoapp.database.OrderDetailEntity

class adapter_kasirTambahPesananan(private val dataDetail: MutableList<OrderDetailEntity>) :

    RecyclerView.Adapter<adapter_kasirTambahPesananan.daftarOrderDetailsViewHolder>() {
    inner class daftarOrderDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaMakanan = itemView.findViewById<TextView>(R.id.tv_namaItem)
        val tvQuantityMakanan = itemView.findViewById<TextView>(R.id.tv_quantity)
        val btnAddQuantity = itemView.findViewById<ImageButton>(R.id.imageButton_addQuantity)
        val btnSubtractQuantity =
            itemView.findViewById<ImageButton>(R.id.imageButton_subtractQuantity)
    }

    override fun getItemCount(): Int {
        return dataDetail.size
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): daftarOrderDetailsViewHolder {
        val view: View = View.inflate(parent.context, R.layout.item_show_order_details, null)
        return daftarOrderDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: daftarOrderDetailsViewHolder, position: Int) {
        val daftar = dataDetail[position]
        holder.tvNamaMakanan.setText(daftar.menuItemName)
        holder.tvQuantityMakanan.setText(daftar.quantity)

        holder.btnAddQuantity.setOnClickListener {
            var quantity = daftar.quantity
            quantity++
            daftar.quantity = quantity.toString().toInt()

            holder.tvQuantityMakanan.text = quantity.toString()
            onItemClickCallback.nambahHarga(daftar)
        }

        holder.btnSubtractQuantity.setOnClickListener {
            var quantity = daftar.quantity
            if (quantity > 1) {
                quantity--
                daftar.quantity = quantity.toString().toInt()
                holder.tvQuantityMakanan.text = quantity.toString()
                onItemClickCallback.kurangHarga(daftar)
            }

        }

    }
    fun isi1Data(daftar: OrderDetailEntity) {
        dataDetail.add(daftar)
        notifyDataSetChanged()
    }
    fun isiData(daftar: MutableList<OrderDetailEntity>){
        dataDetail.clear()
        dataDetail.addAll(daftar)
        notifyDataSetChanged()
    }
    fun getData(): MutableList<OrderDetailEntity> {
        return dataDetail.toMutableList()
    }
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback{
        fun nambahHarga(daftarOrder: OrderDetailEntity)
        fun kurangHarga(daftarOrder: OrderDetailEntity)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}
