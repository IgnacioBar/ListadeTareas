package com.example.listadetareas.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.listadetareas.databinding.ActivityMainBinding
import com.example.listadetareas.ui.model.TasksModel

class MainActivity : AppCompatActivity(), OnClickListener {
    //private lateinit var recyclerView: RecyclerView
    private var tasks: MutableList<TasksModel> = mutableListOf()

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: TasksAdapter
    //LinearLayout
    private lateinit var mLinearLayout: LayoutManager
    //GridLayout
    //private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initRecyclerView()

        // Manejar el clic en el botón de agregar tarea
        mBinding.addButton.setOnClickListener {
            // Obtener el texto ingresado en el campo de texto
            val taskName = mBinding.taskEditText.text.toString().trim()
            val taskDescription = mBinding.etDescription.text.toString().trim()

            // Verificar si el texto no está vacío
            if (taskName.isNotEmpty()) {
                // Agregar la tarea a la lista
                tasks.add(
                    TasksModel(id = System.currentTimeMillis(),  name = taskName, description = taskDescription)
                )
                Log.i("DEVELOPRAFA",tasks.toString())
                // Notificar al adaptador sobre la nueva tarea agregada
                mBinding.recyclerView.adapter?.notifyItemInserted(tasks.size - 1)

                // Limpiar el campo de texto
                mBinding.taskEditText.text.clear()
                mBinding.etDescription.text.clear()
            }
        }
    }

    private fun initRecyclerView() {
        //una unica columna
        mLinearLayout = LinearLayoutManager(this)
        mAdapter = TasksAdapter(tasks, this)
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
        val position = tasks.indexOfFirst { it.id == tasksmodel.id }
        tasks.removeAt(position)
        // Notificar al adaptador sobre el cambio en la lista
        mBinding.recyclerView.adapter?.notifyItemRemoved(position)
        Toast.makeText(this, "Item eliminado", Toast.LENGTH_SHORT).show()

    }

    override fun onUpdateTasks(tasksmodel: TasksModel) {
        val position = tasks.indexOfFirst { it.id == tasksmodel.id }
        tasks.forEach {
            if(it.id == tasksmodel.id) {
                it.finish = tasksmodel.finish
                it.name = tasksmodel.name
                it.description = tasksmodel.description
            }
        }
        mBinding.recyclerView.adapter?.notifyItemChanged(position)
        Toast.makeText(this, "Item actualizado", Toast.LENGTH_SHORT).show()
    }

    override fun onWarning(tasksmodel: TasksModel) {
        Toast.makeText(this,"Has pulsado la tarea: ${tasksmodel.name} ",Toast.LENGTH_SHORT).show()
    }

}
