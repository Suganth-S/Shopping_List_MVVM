package com.suganth.shoppinglistmvvm.dialog

import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItemEntity)
}