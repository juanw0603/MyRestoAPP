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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val radio_owner = findViewById<RadioButton>(R.id.rb_owner)
        val radio_kasirAtauWaiter = findViewById<RadioButton>(R.id.rb_kasirAtauWaiter)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{

            if(etUsername.text.isEmpty() || etPassword.text.isEmpty()){
                Toast.makeText(this, "username atau passoword tidak boleh kosong!!!!",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (radio_owner.isChecked){
                startActivity(Intent(this@MainActivity,ownerPage::class.java))
            }

            if (radio_kasirAtauWaiter.isChecked){
                startActivity(Intent(this@MainActivity,kasirWaiter_page::class.java))
            }

        }


    }
}