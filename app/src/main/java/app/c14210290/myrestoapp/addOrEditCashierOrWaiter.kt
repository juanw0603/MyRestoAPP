package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.c14210290.myrestoapp.database.*

class addOrEditCashierOrWaiter : AppCompatActivity() {
    lateinit var DB: RestoDB


    override fun onCreate(savedInstanceState: Bundle?) {
        DB = RestoDB.getdatabase(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_or_edit_cashier_or_waiter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var dataNama = intent.getStringExtra("nama")
        var dataUsername = intent.getStringExtra("username")
        var dataEmail = intent.getStringExtra("email")
        var dataPassword = intent.getStringExtra("password")


        var etNamaUser = findViewById<EditText>(R.id.etNamaUser)
        var etusernameUser = findViewById<EditText>(R.id.etusernameUser)
        var etemailUser = findViewById<EditText>(R.id.etemailUser)
        var etpasswordUser = findViewById<EditText>(R.id.etpasswordUser)


        val btn_tambah = findViewById<Button>(R.id.btn_addUser)
        val btn_edit = findViewById<Button>(R.id.btn_editUser)

        if (intent.getIntExtra("edit", 0) == 1){
            btn_tambah.visibility = Button.GONE
            btn_tambah.isEnabled = false

            etNamaUser.setText(dataNama)
            etusernameUser.setText(dataUsername)
            etemailUser.setText(dataEmail)
            etpasswordUser.setText(dataPassword)

            btn_edit.setOnClickListener {
                DB.funCashierOrWaiterDao().updateCashierOrWaiter(
                    etNamaUser.text.toString(),
                    etusernameUser.text.toString(),
                    etemailUser.text.toString(),
                    etpasswordUser.text.toString(),
                    intent.getIntExtra("id", 0)
                )
                Toast.makeText(this, "data ke " + intent.getIntExtra("id", 0) +" di ubah !!!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@addOrEditCashierOrWaiter, ownerPage::class.java))
            }

        }else {
            btn_edit.visibility = Button.GONE
            btn_edit.isEnabled = false

        }

        btn_tambah.setOnClickListener {

            if (etNamaUser.text.toString().isEmpty() || etusernameUser.text.toString().isEmpty() || etemailUser.text.toString().isEmpty() || etpasswordUser.text.toString().isEmpty()){
                Toast.makeText(this, "semua input field tidak boleh kosong !!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!etemailUser.text.contains("@gmail.com")){
                Toast.makeText(this, "alamat email tidak valid!!!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            DB.funCashierOrWaiterDao().insertCashierOrWaiter(
                CashierOrWaiterEntity(
                    name = etNamaUser.text.toString(),
                    username = etusernameUser.text.toString(),
                    email = etemailUser.text.toString(),
                    password = etpasswordUser.text.toString()
                )
            )
            startActivity(Intent(this@addOrEditCashierOrWaiter, ownerPage::class.java))
        }


    }
}