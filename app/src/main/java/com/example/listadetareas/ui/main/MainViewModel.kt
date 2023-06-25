package com.example.listadetareas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadetareas.domain.AddTasksUseCase
import com.example.listadetareas.domain.DeleteTasksUseCase
import com.example.listadetareas.domain.GetTasksUseCase
import com.example.listadetareas.domain.UpdateTasksUseCase
import com.example.listadetareas.ui.model.TasksModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 10:51
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class MainViewModel : ViewModel() {

    private var _tasks = MutableLiveData<MutableList<TasksModel>>()
    var tasks: LiveData<MutableList<TasksModel>> = _tasks

    fun onGetAllTasks(){
        //Corrutinas sobre el hilo IO
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = GetTasksUseCase().invoke()
            //Salida al hilo principal antes de finalizar el hilo o corrutina IO
            withContext(Dispatchers.Main){
                _tasks.value = tasks
            }
        }
    }

    fun onDeleteTask(taskModel: TasksModel){
        viewModelScope.launch(Dispatchers.IO) {
            DeleteTasksUseCase().invoke(taskModel)
            onGetAllTasks()
        }
    }

    fun onAddTask(taskModel: TasksModel){
        viewModelScope.launch(Dispatchers.IO) {
            AddTasksUseCase().invoke(taskModel)
            onGetAllTasks()
        }
    }

    fun onUpdateTask(taskModel: TasksModel){
        viewModelScope.launch(Dispatchers.IO) {
            UpdateTasksUseCase().invoke(taskModel)
            onGetAllTasks()
        }
    }

}