package com.suganth.shoppinglistmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suganth.shoppinglistmvvm.data.db.entities.ShoppingItemEntity

/**
 * version - we have to mention that, if we further update our app, need to change the version here,
 * otherwise room will throws an error
 */
@Database(
    entities = [ShoppingItemEntity::class],
    version = 1
)

abstract class ShoppingDatabase: RoomDatabase() {

    /**
     * to access the db operation , we are using abstract method to extract operations from database
     */
    abstract fun getShoppingDao() : ShoppingDao

    companion object{
        /**
         *  from a volatile , we make sure that only one thread is running at a time instance var
         *  of singleton class
         */
        @Volatile
        private var instance: ShoppingDatabase?= null
        private val LOCK = Any()
        /**
         * invoke is callled whenever we create a instance for our shopping class
         * this method called everytime, the instance of shopping database and return our instance,
         * but if our instance is null, we call our synchronized lock , so that no other instance
         * will be set when our synchroize block is running
         */
        operator fun invoke(context: Context): ShoppingDatabase {
            return instance ?: synchronized(LOCK)
            {
                instance ?: createDatabase(context).also { instance = it }
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
        ShoppingDatabase::class.java, "ShoppingDb.db").build()
    }
}