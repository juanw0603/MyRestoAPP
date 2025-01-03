package app.c14210290.myrestoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tableId")
    val tableId: Int = 0 ,
    @ColumnInfo(name = "number")
    val number: Int, // Nomor meja
    @ColumnInfo(name = "status")
    val status: String, // Status meja (kosong, dipesan, selesai)
    @ColumnInfo(name = "currentOrder")
    val currentOrder: Int? = null // ID pesanan saat ini (nullable)
)

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = TableEntity::class,
            parentColumns = ["tableId"],
            childColumns = ["tableId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    val tableId: Int, // Relasi ke tabel `tables`
    val status: String, // Status pesanan (proses, selesai)
    val totalPrice: Double, // Total harga pesanan
    val createdAt: String, // Waktu dibuat
    val updatedAt: String // Waktu diperbarui
)

@Entity(
    tableName = "order_details",
    foreignKeys = [
        ForeignKey(
            entity = OrderEntity::class,
            parentColumns = ["orderId"],
            childColumns = ["orderId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MenuItemEntity::class,
            parentColumns = ["menuItemsId"],
            childColumns = ["menuItemId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderDetailEntity(
    @PrimaryKey(autoGenerate = true) val orderDetailsId: Int = 0,
    val orderId: Int, // Relasi ke tabel `orders`
    val menuItemId: Int, // Relasi ke tabel `menu_items`
    val quantity: Int, // Jumlah item
    val price: Double, // Harga satuan
    val menuItemName: String? = null // Optional, transient field
)



@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true) val menuItemsId: Int = 0,
    val name: String, // Nama menu
    val price: Double // Harga menu
)

@Entity(tableName = "owners")
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ownersId")
    val ownersId: Int = 0, // ID unik
    @ColumnInfo(name = "name")
    val name: String, // Nama owner
    @ColumnInfo(name = "username")
    val username: String, // username untuk login
    @ColumnInfo(name = "email")
    val email: String, // Email owner
    @ColumnInfo(name = "password")
    val password: String // Password (sebaiknya di-hash untuk keamanan)
)

@Entity(tableName = "cashiersOrWaiter")
data class CashierOrWaiterEntity(
    @PrimaryKey(autoGenerate = true) val cashiersOrWaiterId: Int = 0, // ID unik
    val name: String, // Nama cashier/waiter
    val username: String, // username untuk login
    val email: String, // Email untuk login
    val password: String, // Password (sebaiknya di-hash untuk keamanan)
)