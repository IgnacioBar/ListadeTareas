package com.example.listadetareas.domain

import com.example.listadetareas.data.TasksRepository
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.domain
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 17:30
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class DeleteTaskUseCase {

    private var repository = TasksRepository()

    suspend operator fun invoke(taskModel: TaskModel){
        return repository.delete(taskModel)
    }
}