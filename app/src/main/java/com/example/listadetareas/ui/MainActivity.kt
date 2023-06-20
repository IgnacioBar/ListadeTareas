package com.example.listadetareas.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.listadetareas.databinding.ActivityMainBinding
import com.example.listadetareas.ui.adapter.OnClickListener
import com.example.listadetareas.ui.adapter.TasksAdapter
import com.example.listadetareas.ui.model.TaskModel

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private var tasks: MutableList<TaskModel> = mutableListOf()

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

        //Establezco la configuración de observadores del ViewModel
        initViewModel()
        // Inicializar el recyclerView
        initRecyclerView()

        // Manejar el clic en el botón de agregar tarea
        mBinding.btnAddTask.setOnClickListener {
            // Obtener el texto ingresado en el campo de texto
            val taskName = mBinding.etNameTask.text.toString().trim()

            // Verificar si el nombre no está vacío
            if (taskName.isNotEmpty()) {
                // Agregar la tarea a la lista
                mainViewModel.addTask(
                    TaskModel(
                        id = System.currentTimeMillis(),
                        name = mBinding.etNameTask.text.toString().trim(),
                        description = "",
                        finish = false
                    )
                )
                // Limpiar el campo de texto
                mBinding.etNameTask.text.clear()
            }
        }
    }

    private fun initViewModel() {
        mainViewModel.tasks.observe(this) {
            if (it.isNotEmpty())
                mAdapter = TasksAdapter(it, this)
                mBinding.recyclerView.adapter = mAdapter
                mBinding.recyclerView.adapter?.notifyItemInserted(tasks.size - 1)
        }
    }

    //Esta funcion se llama desde el OnCreate
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

    override fun onDeleteTask(taskModel: TaskModel) {
        // Eliminar la tarea de la lista
        //Obtenemos la posicion del item y lo eliminamos
        val position = tasks.indexOfFirst { it.id == taskModel.id }
        tasks.removeAt(position)
        // Notificar al adaptador sobre el cambio en la lista
        mBinding.recyclerView.adapter?.notifyItemRemoved(position)
    }

    override fun onUpdateTask(taskModel: TaskModel) {
        val position = tasks.indexOfFirst { it.id == taskModel.id }
        tasks.forEach {
            if (it.id == taskModel.id) {
                it.finish = taskModel.finish
                it.name = taskModel.name
                it.description = taskModel.description
            }
        }
        mBinding.recyclerView.adapter?.notifyItemChanged(position)
        Toast.makeText(this, "Item actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun onClickItem(taskModel: TaskModel) {
        Toast.makeText(this, "Has pulsado la tarea: ${taskModel.name}", Toast.LENGTH_SHORT).show()
    }
}
