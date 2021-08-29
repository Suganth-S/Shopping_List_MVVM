package com.suganth.shoppinglistmvvm.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity
import com.suganth.shoppinglistmvvm.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    /**
     * Dispatchers.Main = when creating coroutines, we call this, which means it tells to kotlin that
     * in which context we want to start it, in this case we start in main thread, Room basically
     * provide Main safety , and its fine to launch it in the main context
     */
    fun upsert(item: ShoppingItemEntity) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItemEntity) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}