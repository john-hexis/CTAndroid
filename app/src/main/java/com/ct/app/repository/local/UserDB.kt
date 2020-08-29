package com.ct.app.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ct.app.repository.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {

        private var INSTANCE: UserDB? = null

        fun getDatabase(context: Context): UserDB {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, UserDB::class.java, "CT.db")
                    .fallbackToDestructiveMigration().build()
            }
            return INSTANCE as UserDB
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}