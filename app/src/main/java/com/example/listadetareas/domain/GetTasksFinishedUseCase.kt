package com.example.listadetareas.domain

import com.example.listadetareas.data.TasksRepository
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.domain
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 17:29
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class GetTasksFinishedUseCase {

    private var repository = TasksRepository()

    operator fun invoke(): MutableList<TaskModel>{
        return repository.tasksFinished
    }
}