package com.example.listadetareas.ui.model

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 11:18
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
data class TasksModel(
    val id: Long = 0,
    var name: String,
    var description: String = "",
    var finish: Boolean = false)
