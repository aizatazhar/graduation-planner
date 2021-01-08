package com.example.graduation_planner.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.graduation_planner.models.Module

@Dao
interface SavedModulesDao {
    @Query("SELECT * FROM saved_modules")
    fun getAll(): LiveData<List<Module>>

    @Delete
    fun delete(module: Module)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(module: Module)

    @Query("DELETE FROM saved_modules")
    fun clear()
}