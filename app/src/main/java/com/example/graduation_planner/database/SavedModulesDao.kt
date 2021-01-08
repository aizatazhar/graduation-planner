package com.example.graduation_planner.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.graduation_planner.models.Module

@Dao
interface SavedModulesDao {
    @Query("SELECT * FROM saved_modules")
    fun getAll(): List<Module>

    @Delete
    fun delete(module: Module)

    @Insert
    fun insert(module: Module)

    @Query("DELETE FROM saved_modules")
    fun clear()
}