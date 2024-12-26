package app.c14210290.myrestoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TableEntity::class, OrderEntity::class, OrderDetailEntity::class, MenuItemEntity::class, OwnerEntity::class, CashierOrWaiterEntity::class], version = 1)
abstract class RestoDB : RoomDatabase() {

    abstract fun funtableDao(): TableDao
    abstract fun funorderDao(): OrderDao
    abstract fun funorderDetailDao(): OrderDetailDao
    abstract fun funmenuItemDao(): MenuItemDao
    abstract fun funownerDao(): OwnerDao

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
                    ).allowMainThreadQueries().build()
                }
            }

            return INSTANCE as RestoDB
        }
    }
}