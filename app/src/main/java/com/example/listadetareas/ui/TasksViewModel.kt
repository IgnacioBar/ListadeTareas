package com.example.listadetareas.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadetareas.domain.AddTaskUseCase
import com.example.listadetareas.domain.DeleteTaskUseCase
import com.example.listadetareas.domain.GetTasksUseCase
import com.example.listadetareas.domain.UpdateTaskUseCase
import com.example.listadetareas.ui.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private var _tasksFinished = MutableLiveData<MutableList<TaskModel>>()
    val tasksFinished: LiveData<MutableList<TaskModel>> = _tasksFinished

    fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = GetTasksUseCase().invoke()
            withContext(Dispatchers.Main) {
                _tasks.value = tasks
            }
        }
    }

    fun getAllTasksFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = GetTasksUseCase().invoke().filter { tasks->
                tasks.finish
            }
            withContext(Dispatchers.Main) {
                _tasksFinished.value = tasks.toMutableList()
            }
        }
    }

    fun addTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            AddTaskUseCase().invoke(taskModel)
            getAllTasks()
        }
    }

    fun onDeleteTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            DeleteTaskUseCase().invoke(taskModel)
            getAllTasks()
        }
    }

    fun onUpdateTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            UpdateTaskUseCase().invoke(taskModel)
            getAllTasks()
        }
    }
}
