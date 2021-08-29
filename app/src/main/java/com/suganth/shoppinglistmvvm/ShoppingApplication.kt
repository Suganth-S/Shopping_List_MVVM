package com.suganth.shoppinglistmvvm

import android.app.Application
import com.suganth.shoppinglistmvvm.data.db.ShoppingDatabase
import com.suganth.shoppinglistmvvm.repositories.ShoppingRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication: Application(), KodeinAware {
    /**
     * lazy- means that inside of the block we can use application context during binding time
     * because we need that for our shopping database which takes context as parameter
     */
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton { ShoppingDatabase(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
        bind() from provider {  }
    }
}