package app.c14210290.myrestoapp.database


import androidx.room.*


@Dao
interface TableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertTable(table: TableEntity)

    @Update
     fun updateTable(table: TableEntity)

    @Delete
     fun deleteTable(table: TableEntity)

    @Query("SELECT * FROM tables WHERE tableId = :tableId")
     fun getTableById(tableId: Int): TableEntity?

    @Query("SELECT * FROM tables")
     fun getAllTables(): MutableList<TableEntity>
}

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertOrder(order: OrderEntity): Long

    @Update
     fun updateOrder(order: OrderEntity)

    @Delete
     fun deleteOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE orderId = :orderId")
     fun getOrderById(orderId: Int): OrderEntity?

    @Query("SELECT * FROM orders WHERE tableId = :tableId")
     fun getOrdersByTableId(tableId: Int): MutableList<OrderEntity>

    @Query("SELECT * FROM orders")
     fun getAllOrders(): MutableList<OrderEntity>
}


@Dao
interface OrderDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertOrderDetail(orderDetail: OrderDetailEntity): Long

    @Update
     fun updateOrderDetail(orderDetail: OrderDetailEntity)

    @Delete
     fun deleteOrderDetail(orderDetail: OrderDetailEntity)

    @Query("SELECT * FROM order_details WHERE orderId = :orderId")
     fun getOrderDetailsByOrderId(orderId: Int): MutableList<OrderDetailEntity>

    @Query("SELECT * FROM order_details")
     fun getAllOrderDetails(): MutableList<OrderDetailEntity>
}

@Dao
interface MenuItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMenuItem(menuItem: MenuItemEntity): Long

    @Update
     fun updateMenuItem(menuItem: MenuItemEntity)

    @Delete
     fun deleteMenuItem(menuItem: MenuItemEntity)

    @Query("SELECT * FROM menu_items WHERE menuItemsId = :menuItemId")
     fun getMenuItemById(menuItemId: Int): MenuItemEntity?

    @Query("SELECT * FROM menu_items")
     fun getAllMenuItems(): MutableList<MenuItemEntity>
}


@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertOwner(owner: OwnerEntity): Long

    @Update
     fun updateOwner(owner: OwnerEntity)

    @Delete
     fun deleteOwner(owner: OwnerEntity)

    @Query("SELECT * FROM owners WHERE username = :username AND password = :password")
     fun getOwnerByEmailAndPassword(username: String, password: String): OwnerEntity?

    @Query("SELECT * FROM owners")
     fun getAllOwners(): MutableList<OwnerEntity>
}

@Dao
interface CashierOrWaiterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCashierOrWaiter(cashierOrWaiter: CashierOrWaiterEntity): Long

    @Query("UPDATE cashiersOrWaiter SET name = :name, username = :username, email = :email, password = :password WHERE cashiersOrWaiterId = :cashiersOrWaiterId")
     fun updateCashierOrWaiter(name: String, username: String, email: String, password: String, cashiersOrWaiterId: Int)

    @Delete
     fun deleteCashierOrWaiter(cashierOrWaiter: CashierOrWaiterEntity)

    @Query("SELECT * FROM cashiersOrWaiter WHERE username = :username AND password = :password")
     fun getCashierOrWaiterByUserAndPassword(username: String, password: String): CashierOrWaiterEntity?

    @Query("SELECT * FROM cashiersOrWaiter")
     fun getAllCashiersOrWaiters(): MutableList<CashierOrWaiterEntity>
}