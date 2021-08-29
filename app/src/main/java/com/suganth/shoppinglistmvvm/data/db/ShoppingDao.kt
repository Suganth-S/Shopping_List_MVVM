package com.suganth.shoppinglistmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity

@Dao
interface ShoppingDao {

    /**
     * we using onConflict strategy, because when the shopping item is already existed in db means
     * it will be replaced
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItemEntity)

    /**
     * suspend - a keyword to indicate that we are handling this function asynchronously
     * through either thread or co-routines
     */
    @Delete
    suspend fun delete(item: ShoppingItemEntity)

    /**
     * Livedata which returns list of shopping item
     */
    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItemEntity>>
}