package app.c14210290.myrestoapp.database


import androidx.room.*


@Dao
interface TableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTable(table: TableEntity): Long

    @Update
    suspend fun updateTable(table: TableEntity)

    @Delete
    suspend fun deleteTable(table: TableEntity)

    @Query("SELECT * FROM tables WHERE tableId = :tableId")
    suspend fun getTableById(tableId: Int): TableEntity?

    @Query("SELECT * FROM tables")
    suspend fun getAllTables(): List<TableEntity>
}

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity): Long

    @Update
    suspend fun updateOrder(order: OrderEntity)

    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE orderId = :orderId")
    suspend fun getOrderById(orderId: Int): OrderEntity?

    @Query("SELECT * FROM orders WHERE tableId = :tableId")
    suspend fun getOrdersByTableId(tableId: Int): List<OrderEntity>

    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<OrderEntity>
}


@Dao
interface OrderDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderDetail(orderDetail: OrderDetailEntity): Long

    @Update
    suspend fun updateOrderDetail(orderDetail: OrderDetailEntity)

    @Delete
    suspend fun deleteOrderDetail(orderDetail: OrderDetailEntity)

    @Query("SELECT * FROM order_details WHERE orderId = :orderId")
    suspend fun getOrderDetailsByOrderId(orderId: Int): List<OrderDetailEntity>

    @Query("SELECT * FROM order_details")
    suspend fun getAllOrderDetails(): List<OrderDetailEntity>
}

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItem(menuItem: MenuItemEntity): Long

    @Update
    suspend fun updateMenuItem(menuItem: MenuItemEntity)

    @Delete
    suspend fun deleteMenuItem(menuItem: MenuItemEntity)

    @Query("SELECT * FROM menu_items WHERE menuItemsId = :menuItemId")
    suspend fun getMenuItemById(menuItemId: Int): MenuItemEntity?

    @Query("SELECT * FROM menu_items")
    suspend fun getAllMenuItems(): List<MenuItemEntity>
}


@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: OwnerEntity): Long

    @Update
    suspend fun updateOwner(owner: OwnerEntity)

    @Delete
    suspend fun deleteOwner(owner: OwnerEntity)

    @Query("SELECT * FROM owners WHERE email = :email AND password = :password")
    suspend fun getOwnerByEmailAndPassword(email: String, password: String): OwnerEntity?

    @Query("SELECT * FROM owners")
    suspend fun getAllOwners(): List<OwnerEntity>
}

@Dao
interface CashierOrWaiterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCashierOrWaiter(cashierOrWaiter: CashierOrWaiterEntity): Long

    @Update
     fun updateCashierOrWaiter(cashierOrWaiter: CashierOrWaiterEntity)

    @Delete
     fun deleteCashierOrWaiter(cashierOrWaiter: CashierOrWaiterEntity)

    @Query("SELECT * FROM cashiersOrWaiter WHERE email = :email AND password = :password")
     fun getCashierOrWaiterByEmailAndPassword(email: String, password: String): CashierOrWaiterEntity?

    @Query("SELECT * FROM cashiersOrWaiter")
     fun getAllCashiersOrWaiters(): List<CashierOrWaiterEntity>
}