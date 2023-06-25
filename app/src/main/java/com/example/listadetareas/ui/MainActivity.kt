package com.example.listadetareas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.listadetareas.R
import com.example.listadetareas.databinding.ActivityMainBinding
import com.example.listadetareas.databinding.NewTaskBinding
import com.example.listadetareas.ui.adapter.OnClickListener
import com.example.listadetareas.ui.adapter.TasksAdapter
import com.example.listadetareas.ui.model.TaskModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: TasksAdapter

    //LinearLayout
    private lateinit var mLinearLayout: LayoutManager
    //GridLayout
    //private lateinit var mGridLayout: GridLayoutManager

    private val mainViewModel by viewModels<TasksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // Inicializar el recyclerView
        initRecyclerView()
        //Establezco la configuración de observadores del ViewModel
        initViewModel()

        // Manejar el clic en el botón de agregar tarea
        mBinding.btnAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun initRecyclerView() {
        //una unica columna
        mAdapter = TasksAdapter(mutableListOf(), this)
        mLinearLayout = LinearLayoutManager(this)
        //Grid
        // mGridLayout = GridLayoutManager(this, spanCount)
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout //mGridLayout
            adapter = mAdapter
        }
    }

    private fun initViewModel() {

        mainViewModel.getAllTasks()

        mainViewModel.tasks.observe(this) { tasksList ->
            //Si el listado de tareas cambia, se actualiza el RV
            mAdapter = TasksAdapter(tasksList, this)
            mBinding.recyclerView.adapter = mAdapter
            mBinding.recyclerView.adapter?.notifyItemChanged(tasksList.size - 1)
        }
    }

    override fun onDeleteTask(taskModel: TaskModel) {
        // Eliminar la tarea de la lista
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.text_title_alert_delete))
            .setMessage(getString(R.string.text_explain_delete).plus("${taskModel.name}?"))
            .setNegativeButton("Descartar") { _, _ ->
                // Respond to neutral button press
            }
            .setPositiveButton("Eliminar") { _, _ ->
                mainViewModel.onDeleteTask(taskModel)
                Toast.makeText(this, "Item eliminado", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    override fun onUpdateTask(taskModel: TaskModel) {
        //Actualizar la tarea seleccionada
        mainViewModel.onUpdateTask(taskModel)
        Toast.makeText(this, "Item actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun onClickItem(taskModel: TaskModel) {
        Toast.makeText(this, "Has pulsado la tarea: ${taskModel.name}", Toast.LENGTH_SHORT).show()
    }

    private fun showDialog() {

        val dialogBinding = NewTaskBinding.inflate(LayoutInflater.from(this))
        val dialogView: View = dialogBinding.root

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Añadir Tarea")
            .setCancelable(false)

        val dialog: AlertDialog = dialogBuilder.create()
        dialog.show()

        dialogBinding.btnCancelTask.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnAddTaskDialog.setOnClickListener {

            val name = dialogBinding.etNameTaskDialog.text.toString()
            val description = dialogBinding.etDescriptionDialog.text.toString()

            if (name.isNotEmpty()) {
                // Agregar la tarea a la lista
                mainViewModel.addTask(
                    TaskModel(
                        name = name.trim(),
                        description = description.trim(),
                        finish = false
                    )
                )
                dialog.dismiss()
            }
        }
    }
}