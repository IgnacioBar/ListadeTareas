package com.example.listadetareas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.R
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 17/06/2023 at 18:33
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class TasksAdapter(private val taskModel: MutableList<TaskModel>,
                   private var listener:OnClickListener) :
    RecyclerView.Adapter<TaskViewHolder>() {

    //Devuelve el item al ViewHolder por cada objeto del listado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(layoutInflater.inflate(R.layout.item_task,parent,false))
    }

    //Por cada uno de los items llama a render
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       val item = taskModel[position]
       holder.render(item,listener)
    }

    override fun getItemCount(): Int = taskModel.size

}
