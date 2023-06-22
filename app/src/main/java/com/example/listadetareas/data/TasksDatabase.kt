package com.example.listadetareas.data

import androidx.room.Database
import androidx.room.RoomDatabase

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.data
 *
 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 12:22
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Database(entities = [TasksEntity::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract fun taskDao():TasksDao
}