package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.c14210290.myrestoapp.database.RestoDB

class MainActivity : AppCompatActivity() {
    private lateinit var DB:RestoDB

    override fun onCreate(savedInstanceState: Bundle?) {
        DB = RestoDB.getdatabase(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsername = findViewById<EditText>(R.id.et_nameProfile)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val radio_owner = findViewById<RadioButton>(R.id.rb_owner)
        val radio_kasirAtauWaiter = findViewById<RadioButton>(R.id.rb_kasirAtauWaiter)

        val btnLogin = findViewById<Button>(R.id.btn_Login)
        val btnRegister = findViewById<Button>(R.id.btn_register)



        btnLogin.setOnClickListener{

            if(etUsername.text.isEmpty() || etPassword.text.isEmpty()){
                Toast.makeText(this, "username atau passoword tidak boleh kosong!!!!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (radio_owner.isChecked){
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                if (DB.funownerDao().getOwnerByEmailAndPassword(username,password) == null){
                    Toast.makeText(this, "username atau passoword salah!!!!",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                startActivity(Intent(this@MainActivity,ownerPage::class.java))
            }

            if (radio_kasirAtauWaiter.isChecked){
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                if (DB.funCashierOrWaiterDao().getCashierOrWaiterByUserAndPassword(username,password) == null){
                    Toast.makeText(this, "username atau passoword salah!!!!",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                startActivity(Intent(this@MainActivity,kasirWaiter_page::class.java))
            }

        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity,register::class.java))
        }
    }
}