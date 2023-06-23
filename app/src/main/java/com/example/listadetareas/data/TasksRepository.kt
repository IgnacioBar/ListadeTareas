package com.example.listadetareas.data

import com.example.listadetareas.TasksApplication
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.data
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 16:39
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TasksRepository {

    private val dao = TasksApplication.database.taskDao()

    // A nivel de aruiqtectura lo ideal es poner TaskModel y no TaskEntity
    // ya que esta llamada va a ser realizada desde el dominio/ui, y no tiene porque
    // recibir una entidad, ya que trabaja con modelos.
    // Por eso hay que hacer un mapper, es decir, transformar un model a una Entity.

    //Primero recuperamos todas las tasks
    val tasks: MutableList<TaskModel> = dao.getTasks().map { entity ->
        TaskModel(entity.id, entity.name, entity.description, entity.finish)
    }.toMutableList()

    //Ahora recuperamos todas las tasks finalizadas
    val tasksFinished: MutableList<TaskModel> = dao.getTasksFinished().map { entity ->
        TaskModel(entity.id, entity.name, entity.description, entity.finish)
    }.toMutableList()

    suspend fun add(taskModel: TaskModel) {
        dao.addTask(
            TasksEntity(
                taskModel.id,
                taskModel.name,
                taskModel.description,
                taskModel.finish
            )
        )
    }

    suspend fun update(taskModel: TaskModel){
        dao.updateTask(
            TasksEntity(
                taskModel.id,
                taskModel.name,
                taskModel.description,
                taskModel.finish
            )
        )
    }

    suspend fun delete(taskModel: TaskModel){
        dao.deleteTask(
            TasksEntity(
                taskModel.id,
                taskModel.name,
                taskModel.description,
                taskModel.finish
            )
        )
    }
}