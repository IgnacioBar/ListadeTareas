package com.example.listadetareas.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.databinding.ItemTaskBinding
import com.example.listadetareas.ui.model.TaskModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui.model
 *
 * Created by Rafael Barbeyto Torrellas on 18/06/2023 at 20:27
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mBinding = ItemTaskBinding.bind(view)

    fun render(taskModel: TaskModel, listener: OnClickListener) {
        //Incoporamos los datos a cada uno de los Items.
        // Repetir para todos lo elementos del modelo y del item
        mBinding.titleTask.setText(taskModel.name)
        mBinding.etDescription.setText(taskModel.description)
        mBinding.cbFinish.isChecked = taskModel.finish

        //Si quieres click sobre toda la celda
        itemView.setOnClickListener { listener.onClickItem(taskModel) }
        //Si quieres un click largo sobre toda la celda
        itemView.setOnLongClickListener {
            listener.onDeleteTask(taskModel = taskModel)
            true
        }

        //Click sobre el borrado del item
        mBinding.imgDelete.setOnClickListener { listener.onDeleteTask(taskModel = taskModel) }

        mBinding.imgReload.setOnClickListener {
            val updateTask = TaskModel(
                id = taskModel.id,
                name = mBinding.titleTask.text.toString(),
                description = mBinding.etDescription.text.toString(),
                finish = mBinding.cbFinish.isChecked
            )
            listener.onUpdateTask(taskModel = updateTask)
        }

    }

}
