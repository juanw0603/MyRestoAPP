package app.c14210290.myrestoapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.c14210290.myrestoapp.database.MenuItemEntity
import app.c14210290.myrestoapp.database.RestoDB

class activityAddOrEditMakanan : AppCompatActivity() {
    private lateinit var DB: RestoDB
    override fun onCreate(savedInstanceState: Bundle?) {
        DB= RestoDB.getdatabase(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_or_edit_makanan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val et_NamaMakanan = findViewById<EditText>(R.id.et_NamaMakanan)
        val et_harga = findViewById<EditText>(R.id.et_hargaMakanan)

        val btn_edit = findViewById<Button>(R.id.btn_edit)
        val btn_tambah = findViewById<Button>(R.id.btn_tambah)

//        var data = intent.getStringExtra("menuItemsid")
//
//        if (data != null) {
//            btn_tambah.isEnabled = false
//            btn_tambah.isClickable = false
//            btn_tambah.visibility = View.INVISIBLE
//
//        } else {
//            btn_edit.isEnabled = false
//            btn_edit.isClickable = false
//            btn_edit.visibility = View.INVISIBLE
//        }


        btn_tambah.setOnClickListener {

            DB.funmenuItemDao().insertMenuItem(
                MenuItemEntity(
                    name = et_NamaMakanan.text.toString(),
                    price = et_harga.text.toString().toInt().toDouble()
                    )
            )
        }

        btn_edit.setOnClickListener {
            DB.funmenuItemDao().updateMenuItem(
                MenuItemEntity(
                    name = et_NamaMakanan.text.toString(),
                    price = et_harga.text.toString().toInt().toDouble()
                )
            )
        }



    }
}