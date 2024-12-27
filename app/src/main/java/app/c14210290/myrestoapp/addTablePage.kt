package app.c14210290.myrestoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.c14210290.myrestoapp.database.RestoDB
import app.c14210290.myrestoapp.database.TableEntity

class addTablePage : AppCompatActivity() {
    private lateinit var DB: RestoDB
    override fun onCreate(savedInstanceState: Bundle?) {
        DB = RestoDB.getdatabase(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_table_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val et_nomorMeja = findViewById<EditText>(R.id.et_newNomorMeja)
        val btn_addNewMeja = findViewById<Button>(R.id.btn_addNewMeja)

        btn_addNewMeja.setOnClickListener {
            if (et_nomorMeja.text.toString().isEmpty()) {
                Toast.makeText(this, "jangan lupa masukkin nomor meja", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            DB.funtableDao().insertTable(
                TableEntity(
                    number = et_nomorMeja.text.toString().toInt(),
                    status = "kosong",
                    currentOrder = null
                )
            )
        }

    }
}