package com.suganth.shoppinglistmvvm.repositories

import com.suganth.shoppinglistmvvm.data.db.ShoppingDatabase
import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity

/**
 *
 */
class ShoppingRepository(private val db : ShoppingDatabase) {
    /**
     * from the repository, the view models call the methods later
     */
    suspend fun upsert(item: ShoppingItemEntity) = db.getShoppingDao().upsert(item)
    suspend fun delete(item : ShoppingItemEntity) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}