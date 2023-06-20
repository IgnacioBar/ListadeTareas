package com.example.listadetareas.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.databinding.ItemTaskBinding
import com.example.listadetareas.ui.model.TasksModel

/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.ui
 *
 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 11:08
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val mBinding = ItemTaskBinding.bind(view)

    fun render(tasksModel: TasksModel, listener: OnClickListener) {
        //Incoporamos los datos a cada uno de los Items.
        // Repetir para todos lo elementos del modelo y del item
        mBinding.titleTask.setText(tasksModel.name)
        mBinding.etDescription.setText(tasksModel.description)
        mBinding.cbFinish.isChecked = tasksModel.finish

        //Si quieres click sobre toda la celda
        itemView.setOnLongClickListener {
            listener.onDeleteTasks(tasksmodel = tasksModel)
            true
        }

        //Ejemplo de Click sobre un icono de borrado
        mBinding.imgDelete.setOnClickListener { listener.onDeleteTasks(tasksmodel = tasksModel) }

        //Ejemplo de actualizar datos de un item
        mBinding.imgReload.setOnClickListener {
            val updateModel = TasksModel(
                id = tasksModel.id,
                name = mBinding.titleTask.text.toString(),
                description = mBinding.etDescription.text.toString(),
                finish = mBinding.cbFinish.isChecked
            )
            listener.onUpdateTasks(tasksmodel = updateModel)
        }
    }
}
