package com.example.listadetareas.ui

import com.example.listadetareas.ui.model.TasksModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 11:12
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface OnClickListener {

    fun onDeleteTasks(tasksmodel: TasksModel)
    fun onUpdateTasks(tasksmodel: TasksModel)
    fun onWarning(tasksmodel: TasksModel)

}
