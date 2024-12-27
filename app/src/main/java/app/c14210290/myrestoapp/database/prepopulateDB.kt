package app.c14210290.myrestoapp.database

import androidx.activity.result.launch
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import android.content.Context

import kotlinx.coroutines.launch

class prepopulateDB(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Insert initial data here using db.execSQL() or other methods
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val restoDB = RestoDB.getdatabase(context)
            val ownerDao = restoDB.funownerDao()
            val cashierOrWaiterDao = restoDB.funCashierOrWaiterDao()
            val menuItemDao = restoDB.funmenuItemDao()
            val tableDao = restoDB.funtableDao()

            // Insert owners
            ownerDao.insertOwner(OwnerEntity(name = "Admin", username = "admin", email = "admin@example.com", password = "admin123"))

            // Insert cashiers or waiters
            cashierOrWaiterDao.insertCashierOrWaiter(CashierOrWaiterEntity(name = "Cashier 1", username = "cashier1", email = "cashier1@example.com", password = "cashier123"))
            cashierOrWaiterDao.insertCashierOrWaiter(CashierOrWaiterEntity(name = "Waiter 1", username = "waiter1", email = "waiter1@example.com", password = "waiter123"))

            // Insert menu items
            menuItemDao.insertMenuItem(MenuItemEntity(name = "Nasi Goreng", price = 15000.0))
            menuItemDao.insertMenuItem(MenuItemEntity(name = "Mie Goreng", price = 12000.0))
            menuItemDao.insertMenuItem(MenuItemEntity(name = "Ayam Geprek", price = 20000.0))

            // Insert tables
            for (i in 1..10) {
                tableDao.insertTable(TableEntity(number = i, status = "Kosong"))
            }
        }
    }
}