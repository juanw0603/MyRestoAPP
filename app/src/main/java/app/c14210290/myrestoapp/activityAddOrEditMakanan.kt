package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        if(intent.getIntExtra("edit", 0) == 1){
            var id = intent.getIntExtra("menuItemsid", 0)
            et_NamaMakanan.setText(intent.getStringExtra("MenuName"))
            et_harga.setText(intent.getStringExtra("MenuPrice"))

            btn_tambah.visibility = Button.GONE
            btn_tambah.isEnabled = false
            btn_edit.setOnClickListener {
                Toast.makeText(this, "data ke " + id +" berhasil di ubah !!!", Toast.LENGTH_LONG).show()
                DB.funmenuItemDao().updateMenuItemById(id, et_NamaMakanan.text.toString(), et_harga.text.toString().toDouble())
                startActivity(Intent(this@activityAddOrEditMakanan, ownerPage::class.java))
            }
        } else{
            btn_edit.visibility = Button.GONE
            btn_edit.isEnabled = false
        }


        btn_tambah.setOnClickListener {

            DB.funmenuItemDao().insertMenuItem(
                MenuItemEntity(
                    name = et_NamaMakanan.text.toString(),
                    price = et_harga.text.toString().toInt().toDouble()
                    )
            )

            Toast.makeText(this, "data berhasil ditambahkan !!!", Toast.LENGTH_LONG).show()
            finish()

            startActivity(Intent(this@activityAddOrEditMakanan, ownerPage::class.java))
        }





    }
}