package com.example.listadetareas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 16/06/2023 at 22:10
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TasksViewModel : ViewModel() {

    private var _tasks = MutableLiveData<MutableList<TaskModel>>()
    val tasks: LiveData<MutableList<TaskModel>> = _tasks

    fun addTask(taskModel: TaskModel) {
        val currentTasks = _tasks.value ?: mutableListOf() // Obtener la lista actual o crear una nueva si es nula
        currentTasks.add(taskModel) // Agregar el nuevo elemento
        _tasks.value = currentTasks // Actualizar el valor de _tasks
    }
}
