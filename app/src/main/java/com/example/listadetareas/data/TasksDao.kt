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
 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 12:36
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Dao
interface TasksDao {

    @Query("SELECT * FROM tasksList")
    fun getAllTasks(): MutableList<TasksEntity>

    @Insert
    suspend fun addTask(taskEntity: TasksEntity)

    @Update
    suspend fun updateTask(taskEntity: TasksEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TasksEntity)

}