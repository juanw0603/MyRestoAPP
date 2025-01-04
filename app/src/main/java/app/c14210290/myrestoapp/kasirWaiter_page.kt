package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.dataClass.SelectedTable
import app.c14210290.myrestoapp.database.OrderDetailEntity
import app.c14210290.myrestoapp.database.OrderEntity
import app.c14210290.myrestoapp.database.RestoDB
import app.c14210290.myrestoapp.database.TableEntity

class kasirWaiter_page : AppCompatActivity() {
    private lateinit var DB: RestoDB
    private lateinit var AdapterMeja: adapter_kasirMeja
    private var arMeja: MutableList<TableEntity> = mutableListOf()
    private lateinit var AdapterShowPesananDanJumlah: adapter_showPesananDanJumlah
    private var arShowPesananDanJumlah: MutableList<OrderDetailEntity> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DB = RestoDB.getdatabase(this)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kasir_waiter_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AdapterMeja = adapter_kasirMeja(arMeja)
        val rvMeja = findViewById<RecyclerView>(R.id.rv_daftarMeja)

        rvMeja.layoutManager = LinearLayoutManager(this)
        rvMeja.adapter = AdapterMeja

        val dataMeja = DB.funtableDao().getAllTables()
        AdapterMeja.isiData(dataMeja)



        AdapterShowPesananDanJumlah = adapter_showPesananDanJumlah(arShowPesananDanJumlah)
        val rvShowPesananDanJumlah = findViewById<RecyclerView>(R.id.rv_listPesanan)

        rvShowPesananDanJumlah.layoutManager = LinearLayoutManager(this)
        rvShowPesananDanJumlah.adapter = AdapterShowPesananDanJumlah


        val tvCurrentTableNumber = findViewById<TextView>(R.id.tv_CurrentTableNumber)

        var selectedTable: SelectedTable? = null

        AdapterMeja.setOnItemClickCallback(object : adapter_kasirMeja.OnItemClickCallback {
            override fun getId(daftarMeja: TableEntity) {
                // Store the instance in the variable
                selectedTable = SelectedTable(daftarMeja.tableId)

                // Update the TextView to display the selected table number
                tvCurrentTableNumber.text = "table no : ${selectedTable?.tableId}"

                val currentOrder =  DB.funorderDao().getOrderByTableIdAndStatusProces(selectedTable?.tableId ?: 0)

                if (currentOrder != null) {
                    val orderDetails = DB.funorderDetailDao().getOrderDetailsByOrderId(currentOrder.orderId)
                    AdapterShowPesananDanJumlah.isiData(orderDetails)
                } else {
                    AdapterShowPesananDanJumlah.isiData(mutableListOf())
                    Toast.makeText(this@kasirWaiter_page, "tidak ada order untuk meja ${selectedTable?.tableId}.", Toast.LENGTH_SHORT).show()
                }
            }
        })


        val _btnTransaction = findViewById<Button>(R.id.btn_transaction)
        val btn_addTable = findViewById<Button>(R.id.btn_addTable)
        val btn_tambahPesanan = findViewById<Button>(R.id.btn_tambahPesanan)


        btn_addTable.setOnClickListener {
            val intent = Intent(this@kasirWaiter_page, addTablePage::class.java)
            startActivity(intent)
        }

        btn_tambahPesanan.setOnClickListener {
            // Retrieve the selected table
            val selectedTableId = selectedTable?.tableId ?: 0
            val getCurrentTable = DB.funtableDao().getTableById(selectedTableId)

            if (getCurrentTable != null) {
                Toast.makeText(this, "Your selected table is ${selectedTable?.tableId}", Toast.LENGTH_LONG).show()

                // Check if an active order exists for this table
                var currentOrder = DB.funorderDao().getOrderByTableIdAndStatusProces(selectedTableId)

                if (currentOrder == null) {
                    // Create a new order if none exists
                    val newOrder = OrderEntity(
                        tableId = selectedTableId,
                        status = "proses",
                        totalPrice = 0.0,
                        createdAt = System.currentTimeMillis().toString(), // Replace with proper timestamp format
                        updatedAt = System.currentTimeMillis().toString()  // Replace with proper timestamp format
                    )
                    val newOrderId = DB.funorderDao().insertOrder(newOrder)
                    currentOrder = newOrder.copy(orderId = newOrderId.toInt())
                }

                Toast.makeText(this, "New order detail added.", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(this, "Please select a table first.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}