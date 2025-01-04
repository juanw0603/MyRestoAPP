package app.c14210290.myrestoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.MenuItemEntity
import app.c14210290.myrestoapp.database.OrderDetailEntity
import app.c14210290.myrestoapp.database.RestoDB
import app.c14210290.myrestoapp.database.TableEntity

class tambahAtauEditPesanan : AppCompatActivity() {
    private lateinit var DB: RestoDB
    private lateinit var Adapter_kasirTambahPesananan: adapter_kasirTambahPesananan
    private var arKasirMakananCard: MutableList<OrderDetailEntity> = mutableListOf()
    private lateinit var AdapterMakanan: adapter_KasirMakananCard
    private var arMakanan: MutableList<MenuItemEntity> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        DB = RestoDB.getdatabase(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_atau_edit_pesanan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        AdapterMakanan = adapter_KasirMakananCard(arMakanan)
        val rvMakanan = findViewById<RecyclerView>(R.id.rv_listMakanan)
        rvMakanan.adapter = AdapterMakanan
        rvMakanan.layoutManager = LinearLayoutManager(this)

        AdapterMakanan.isiData(DB.funmenuItemDao().getAllMenuItems())

        val rvTambahPesanan = findViewById<RecyclerView>(R.id.rv_listPesanan)

        Adapter_kasirTambahPesananan = adapter_kasirTambahPesananan(arKasirMakananCard)
        rvTambahPesanan.layoutManager = LinearLayoutManager(this)
        rvTambahPesanan.adapter= Adapter_kasirTambahPesananan

        val tvCurrentTableNumber = findViewById<TextView>(R.id.tv_CurrentTableNumber)

        val selectedtableId = intent.getIntExtra("tableId", 0)

        tvCurrentTableNumber.setText("table no : $selectedtableId")
        val btn_konfirmasiPesanan = findViewById<Button>(R.id.btn_konfirmasiPesanan)
        Adapter_kasirTambahPesananan.setOnItemClickCallback(object : adapter_kasirTambahPesananan.OnItemClickCallback {
            override fun nambahHarga(daftarOrder: OrderDetailEntity) {
                daftarOrder.price += DB.funmenuItemDao().getMenuItemById(daftarOrder.menuItemId).toString().toInt().toDouble()
            }

            override fun kurangHarga(daftarOrder: OrderDetailEntity) {
                daftarOrder.price -= DB.funmenuItemDao().getMenuItemById(daftarOrder.menuItemId).toString().toInt().toDouble()
            }
        })

        val orderId = intent.getIntExtra("orderId", 0)
        if (orderId != 0) {
            val orderDetails = DB.funorderDetailDao().getOrderDetailsByOrderId(orderId)
            Adapter_kasirTambahPesananan.isiData(orderDetails)
            btn_konfirmasiPesanan.setOnClickListener {


            }
        } else{
            val tableId = intent.getIntExtra("tableId", 0)
            val currentTable = DB.funtableDao().getTableById(tableId)
            val orderDetails = DB.funorderDetailDao().getOrderDetailsByOrderId(currentTable?.currentOrder?: 0)
            Adapter_kasirTambahPesananan.isiData(orderDetails)

            btn_konfirmasiPesanan.setOnClickListener {

                val orderDetailsList = Adapter_kasirTambahPesananan.getData()

                // Loop through each order detail and insert it into the database
                for (orderDetail in orderDetailsList) {
                    DB.funorderDetailDao().insertOrderDetail(orderDetail)
                }

                Toast.makeText(this, "Order confirmed successfully!", Toast.LENGTH_SHORT).show()


                finish()
            }

        }
        
        AdapterMakanan.setOnItemClickCallback(object : adapter_KasirMakananCard.OnItemClickCallback {
            override fun getMenu(daftarMenu: MenuItemEntity) {
                Adapter_kasirTambahPesananan.isi1Data(
                    OrderDetailEntity(
                    menuItemId = daftarMenu.menuItemsId,
                    menuItemName = daftarMenu.name,
                    quantity = 1,
                    price = daftarMenu.price,
                    orderId = orderId
                    )
                )
            }
        })







    }




}