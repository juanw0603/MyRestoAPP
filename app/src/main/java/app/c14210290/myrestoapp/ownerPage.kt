package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ownerPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_owner_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mFragmentManager = supportFragmentManager

        val fOwner_DataMenu = Owner_DataMenu()
        val fOwner_LaporanPenjualan = Owner_LaporanPenjualan()
        val fOwner_DaftarWaiterOrCashier = Owner_DaftarWaiterOrCashier()

        val navToDataMenu = findViewById<Button>(R.id.btn_ToDataMenu)
        val navToLaporanPenjualan = findViewById<Button>(R.id.btn_ToLaporanPenjualan)
        val navToDaftarWaiterOrCashier = findViewById<Button>(R.id.btn_ToDaftarWaiterOrCashier)

        val logout = findViewById<Button>(R.id.btn_Logout)


        navToDataMenu.setOnClickListener{
            mFragmentManager.findFragmentByTag(Owner_DataMenu::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .replace(R.id.layoutFragment,fOwner_DataMenu, Owner_DataMenu::class.java.simpleName)
                .commit()
        }

        navToLaporanPenjualan.setOnClickListener {
            mFragmentManager.findFragmentByTag(Owner_LaporanPenjualan::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .replace(R.id.layoutFragment,fOwner_LaporanPenjualan, Owner_LaporanPenjualan::class.java.simpleName)
                .commit()
        }

        navToDaftarWaiterOrCashier.setOnClickListener{
            mFragmentManager.findFragmentByTag(Owner_DaftarWaiterOrCashier::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .replace(R.id.layoutFragment,fOwner_DaftarWaiterOrCashier, Owner_DaftarWaiterOrCashier::class.java.simpleName)
                .commit()
        }

        logout.setOnClickListener{
            startActivity(Intent(this@ownerPage,MainActivity::class.java))
        }

    }
}