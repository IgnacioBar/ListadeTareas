package com.example.listadetareas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.R
import com.example.listadetareas.ui.model.TasksModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 11:07
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
class TasksAdapter(private val nameModel: MutableList<TasksModel>,
                   private var listener:OnClickListener) :
    RecyclerView.Adapter<TasksViewHolder>() {

    //Devuelve el item al ViewHolder por cada objeto del listado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TasksViewHolder(layoutInflater.inflate(R.layout.item_task,parent,false))
    }

    //Por cada uno de los items llama a render
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
       val item = nameModel[position]
       holder.render(item,listener)
    }

    override fun getItemCount(): Int = nameModel.size

}
