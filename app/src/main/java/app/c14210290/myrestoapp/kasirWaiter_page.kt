package app.c14210290.myrestoapp

import android.content.Intent
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
import app.c14210290.myrestoapp.database.RestoDB
import app.c14210290.myrestoapp.database.TableEntity

class kasirWaiter_page : AppCompatActivity() {
    private lateinit var DB: RestoDB
    private lateinit var AdapterMeja: adapter_kasirMeja
    private var arMeja: MutableList<TableEntity> = mutableListOf()
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
        var rvMeja = findViewById<RecyclerView>(R.id.rv_daftarMeja)

        rvMeja.layoutManager = LinearLayoutManager(this)
        rvMeja.adapter = AdapterMeja


        var dataMeja = DB.funtableDao().getAllTables()
        AdapterMeja.isiData(dataMeja)


        val btn_addTable = findViewById<Button>(R.id.btn_addTable)
        btn_addTable.setOnClickListener {
            val intent = Intent(this@kasirWaiter_page, addTablePage::class.java)
            startActivity(intent)
        }

        val _btnTransaction = findViewById<Button>(R.id.btn_transaction)
        _btnTransaction
        _btnTransaction.setOnClickListener {
            val intent = Intent(this, konfirmasiBayarPage::class.java)
            startActivity(intent)
        }




        var tvCurrentTableNumber = findViewById<TextView>(R.id.tv_CurrentTableNumber)


        var selectedTable: SelectedTable? = null

        AdapterMeja.setOnItemClickCallback(object : adapter_kasirMeja.OnItemClickCallback {
            override fun getId(daftarMeja: TableEntity) {
                // Store the instance in the variable
                selectedTable = SelectedTable(daftarMeja.tableId)

                // Update the TextView to display the selected table number
                tvCurrentTableNumber.text = "Selected Table: ${selectedTable?.tableId}"
            }
        })

        val btn_tambahPesanan = findViewById<Button>(R.id.btn_tambahPesanan)
        btn_tambahPesanan.setOnClickListener {
            Toast.makeText(this, "your selected table is ${selectedTable?.tableId}", Toast.LENGTH_LONG).show()
        }


    }
}