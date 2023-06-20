package com.example.listadetareas.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    private fun initRecyclerView() {
        //una unica columna
        mAdapter = TasksAdapter(tasks, this)
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

        mainViewModel.tasks.observe(this) { tasksList ->
            //Si el listado de tareas cambia, se actualiza el RV

            if (tasksList.isNotEmpty())
                mAdapter = TasksAdapter(tasksList, this)
                mBinding.recyclerView.adapter = mAdapter
                mBinding.recyclerView.adapter?.notifyItemChanged(tasksList.size - 1)
        }

    }


    //Esta funcion se llama desde el OnCreate


    override fun onDeleteTask(taskModel: TaskModel) {
        // Eliminar la tarea de la lista
        mainViewModel.onDeleteTask(taskModel)
        Toast.makeText(this, "Item eliminado", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateTask(taskModel: TaskModel) {
        mainViewModel.onUpdateTask(taskModel)
        Toast.makeText(this, "Item actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun onClickItem(taskModel: TaskModel) {
        Toast.makeText(this, "Has pulsado la tarea: ${taskModel.name}", Toast.LENGTH_SHORT).show()
    }
}
