package com.example.listadetareas

import android.app.Application
import androidx.room.Room
import com.example.listadetareas.data.TasksDatabase

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 16:47
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TasksApplication : Application() {

    companion object {
        lateinit var database: TasksDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            TasksDatabase::class.java,
            "tasksList")
            .build()
    }
}