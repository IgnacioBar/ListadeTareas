package com.example.listadetareas.domain

import com.example.listadetareas.data.TasksRepository
import com.example.listadetareas.ui.model.TasksModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.domain
 *
 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 13:34
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class AddTasksUseCase {

    private var repository = TasksRepository()

    suspend operator fun invoke(tasksModel: TasksModel){
        return repository.add(tasksModel)
    }
}