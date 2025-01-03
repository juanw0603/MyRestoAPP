package app.c14210290.myrestoapp.dataClass

data class OrderDetailWithMenuItemName(
    val orderId: Int,
    val menuItemId: Int,
    val quantity: Int,
    val price: Double,
    val menuItemName: String
)
