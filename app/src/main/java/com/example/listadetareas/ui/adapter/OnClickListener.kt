package com.example.listadetareas.ui.adapter

import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 19/06/2023 at 10:42
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface OnClickListener {
    fun onDeleteTask(taskModel: TaskModel)
    fun onUpdateTask(taskModel: TaskModel)
    fun onClickItem(taskModel: TaskModel)
}