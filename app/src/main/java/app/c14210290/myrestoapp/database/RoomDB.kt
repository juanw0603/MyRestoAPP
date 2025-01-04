package app.c14210290.myrestoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


@Database(entities = [TableEntity::class, OrderEntity::class, OrderDetailEntity::class, MenuItemEntity::class, OwnerEntity::class, CashierOrWaiterEntity::class, Pendapatan::class], version = 5)
abstract class RestoDB : RoomDatabase() {

    abstract fun funtableDao(): TableDao
    abstract fun funorderDao(): OrderDao
    abstract fun funorderDetailDao(): OrderDetailDao
    abstract fun funmenuItemDao(): MenuItemDao
    abstract fun funownerDao(): OwnerDao
    abstract fun funCashierOrWaiterDao(): CashierOrWaiterDao
    abstract fun pendapatanDao(): PendapatanDao

    companion object {

        @Volatile
        private var INSTANCE:RestoDB? = null


        @JvmStatic
        fun getdatabase(context: Context): RestoDB {
            if (INSTANCE == null) {
                synchronized(RestoDB::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RestoDB::class.java, "resto_db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE as RestoDB
        }

        fun getDatabase1(context: Context): RestoDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestoDB::class.java,
                    "pendapatan_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Tambahkan data awal ke database
                            Executors.newSingleThreadExecutor().execute {
                                getdatabase(context).pendapatanDao().insertPendapatan(
                                    pendapatan = Pendapatan(totalPendapatan = 12000000.0) // Contoh data awal Rp 12.000.000
                                )
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

