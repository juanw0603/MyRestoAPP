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
import app.c14210290.myrestoapp.database.OwnerEntity
import app.c14210290.myrestoapp.database.RestoDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class register : AppCompatActivity() {
    lateinit var DB:RestoDB

    override fun onCreate(savedInstanceState: Bundle?) {
        DB = RestoDB.getdatabase(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val etRegistNama = findViewById<EditText>(R.id.etRegistNama)
        val etRegistUsername = findViewById<EditText>(R.id.etRegistUsername)
        val etRegistPassword = findViewById<EditText>(R.id.etRegistPassword)
        val etRegistEmail = findViewById<EditText>(R.id.etRegistEmail)

        val btn_addOwner = findViewById<Button>(R.id.btn_addOwner)


        btn_addOwner.setOnClickListener {

            if (etRegistNama.text.toString().isEmpty() || etRegistUsername.text.toString().isEmpty() || etRegistPassword.text.toString().isEmpty() || etRegistEmail.text.toString().isEmpty()){
                Toast.makeText(this, "semua input field tidak boleh kosong!!!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!etRegistEmail.text.contains("@gmail.com")){
                Toast.makeText(this, "alamat email tidak valid!!!!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val nama = etRegistNama.text.toString()
            val username = etRegistUsername.text.toString()
            val password = etRegistPassword.text.toString()
            val email = etRegistEmail.text.toString()

            CoroutineScope(Dispatchers.IO).async {
                DB.funownerDao().insertOwner(
                    OwnerEntity(
                        name = nama,
                        username = username,
                        email = email,
                        password = password
                    )
                )

            }
            Toast.makeText(this, "data ditambahkan", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@register,MainActivity::class.java))
        }

    }
}