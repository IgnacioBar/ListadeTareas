package com.example.listadetareas.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.data
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 16:24
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Dao
interface TasksDao {

    @Query("SELECT * FROM tasksList")
    fun getTasks(): MutableList<TasksEntity>

    @Query("SELECT * FROM tasksList WHERE finish = :finished ")
    fun getTasksFinished(finished: Boolean = true): MutableList<TasksEntity>

    @Insert
    suspend fun addTask(item: TasksEntity)

    @Update
    suspend fun updateTask(item: TasksEntity)

    @Delete
    suspend fun deleteTask(item: TasksEntity)

}