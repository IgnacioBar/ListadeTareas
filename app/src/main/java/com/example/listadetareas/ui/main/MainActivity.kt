package com.example.listadetareas.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.listadetareas.databinding.ActivityMainBinding
import com.example.listadetareas.ui.adapter.OnClickListener
import com.example.listadetareas.ui.adapter.TasksAdapter
import com.example.listadetareas.ui.model.TasksModel

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: TasksAdapter

    //LinearLayout
    private lateinit var mLinearLayout: LayoutManager
    //GridLayout
    //private lateinit var mGridLayout: GridLayoutManager

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initViewModel()
        initRecyclerView()

        // Manejar el clic en el botón de agregar tarea
        mBinding.addButton.setOnClickListener {
            // Obtener el texto ingresado en el campo de texto
            val taskName = mBinding.taskEditText.text.toString().trim()
            val taskDescription = mBinding.etDescription.text.toString().trim()
            // Verificar si el texto del nombre no está vacío
            if (taskName.isNotEmpty()) {
                // Agregar la tarea a la lista
                /*tasks.add(
                    TasksModel(id = System.currentTimeMillis(),  name = taskName, description = taskDescription)
                )*/
                mainViewModel.onAddTask(
                    TasksModel(
                        //id = System.currentTimeMillis(),
                        name = taskName,
                        description = taskDescription
                    )
                )
                // Notificar al adaptador sobre la nueva tarea agregada
                //mBinding.recyclerView.adapter?.notifyItemInserted(tasks.size - 1)

                // Limpiar el campo de texto
                mBinding.taskEditText.text.clear()
                mBinding.etDescription.text.clear()
            }
        }
    }

    private fun initViewModel() {

        mainViewModel.onGetAllTasks()

        mainViewModel.tasks.observe(this) { tasksList ->
            mAdapter = TasksAdapter(tasksList, this)
            mBinding.recyclerView.adapter = mAdapter
            mBinding.recyclerView.adapter?.notifyItemChanged(tasksList.size - 1)
        }
    }

    private fun initRecyclerView() {
        //una unica columna
        mLinearLayout = LinearLayoutManager(this)
        mAdapter = TasksAdapter(mutableListOf(), this)
        //Grid
        // mGridLayout = GridLayoutManager(this, spanCount)
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayout //mGridLayout
            adapter = mAdapter
        }
    }

    override fun onDeleteTasks(tasksmodel: TasksModel) {
        // Eliminar la tarea de la lista
        //Obtenemos la posicion del item y lo eliminamos
        /*val position = tasks.indexOfFirst { it.id == tasksmodel.id }
        tasks.removeAt(position)*/
        // Notificar al adaptador sobre el cambio en la lista
        //mBinding.recyclerView.adapter?.notifyItemRemoved(position)
        mainViewModel.onDeleteTask(tasksmodel)
        Toast.makeText(this, "Item eliminado", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateTasks(tasksmodel: TasksModel) {
        mainViewModel.onUpdateTask(tasksmodel)
        Toast.makeText(this, "Item actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun onWarning(tasksmodel: TasksModel) {
        Toast.makeText(this, "Has pulsado la tarea: ${tasksmodel.name} ", Toast.LENGTH_SHORT).show()
    }

}
