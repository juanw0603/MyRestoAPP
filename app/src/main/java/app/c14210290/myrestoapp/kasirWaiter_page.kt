package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class kasirWaiter_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kasir_waiter_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
    }
}