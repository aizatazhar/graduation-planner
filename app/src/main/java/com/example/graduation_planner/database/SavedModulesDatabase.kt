package com.example.graduation_planner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.graduation_planner.models.Module

@Database(entities = [Module::class], version = 1, exportSchema = false)
abstract class SavedModulesDatabase : RoomDatabase() {
    abstract val savedModulesDao: SavedModulesDao

    companion object {
        @Volatile
        private var INSTANCE: SavedModulesDatabase? = null

        fun getInstance(context: Context): SavedModulesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SavedModulesDatabase::class.java,
                        "saved_modules_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}