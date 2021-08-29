package com.suganth.shoppinglistmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.suganth.shoppinglistmvvm.R
import com.suganth.shoppinglistmvvm.adapter.ShoppingItemAdapter
import com.suganth.shoppinglistmvvm.data.db.ShoppingDatabase
import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity
import com.suganth.shoppinglistmvvm.dialog.AddDialogListener
import com.suganth.shoppinglistmvvm.dialog.AddShoppingItemDialog
import com.suganth.shoppinglistmvvm.repositories.ShoppingRepository
import com.suganth.shoppinglistmvvm.ui.shoppinglist.ShoppingViewModel
import com.suganth.shoppinglistmvvm.ui.shoppinglist.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository )

        val viewModel= ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener{
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItemEntity) {
                        viewModel.upsert(item)
                    }
                }
            ).show()
        }
    }
}