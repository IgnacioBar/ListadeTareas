package com.example.listadetareas.data

import com.example.listadetareas.TasksApplication
import com.example.listadetareas.ui.model.TasksModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.data
 *
 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 12:47
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TasksRepository {

    val tasks: MutableList<TasksModel> = TasksApplication.database.taskDao().getAllTasks().map { entity->
        TasksModel(entity.id,entity.name,entity.description,entity.finish)
    }.toMutableList()

    suspend fun add(tasksModel: TasksModel){
        TasksApplication.database.taskDao().addTask(
            TasksEntity(
                tasksModel.id,
                tasksModel.name,
                tasksModel.description,
                tasksModel.finish
            ))
    }
    suspend fun update(tasksModel: TasksModel){
        TasksApplication.database.taskDao().updateTask(
            TasksEntity(
                tasksModel.id,
                tasksModel.name,
                tasksModel.description,
                tasksModel.finish
            ))
    }
    suspend fun delete(tasksModel: TasksModel){
        TasksApplication.database.taskDao().deleteTask(
            TasksEntity(
                tasksModel.id,
                tasksModel.name,
                tasksModel.description,
                tasksModel.finish
            ))
    }

}