package com.example.listadetareas

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tasks: MutableList<String>
    private lateinit var adapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)

        // Inicializar la lista de tareas y el adaptador
        tasks = mutableListOf()
        adapter = TasksAdapter(tasks)

        // Configurar el RecyclerView con el adaptador y el diseño lineal
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Añadir una línea divisoria entre las tareas
        val dividerDrawable: Drawable? = ContextCompat.getDrawable(this, R.drawable.divider)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerDrawable?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Obtener la referencia al botón de eliminar tarea
        val deleteButton = findViewById<View>(R.id.deleteButton)

        // Manejar el clic en el botón de eliminar tarea
        deleteButton.setOnClickListener {
            // Obtener la posición de la tarea seleccionada
            val position = adapter.getSelectedPosition()

            // Verificar si hay una tarea seleccionada
            if (position != RecyclerView.NO_POSITION) {
                // Eliminar la tarea de la lista
                tasks.removeAt(position)

                // Notificar al adaptador sobre el cambio en la lista
                adapter.notifyItemRemoved(position)

                // Actualizar los números de las tareas en el adaptador
                adapter.updateTaskNumbers()

                // Limpiar la selección de la tarea
                adapter.clearSelection()
            }
        }

        // Obtener la referencia al botón de agregar tarea
        val addButton = findViewById<View>(R.id.addButton)
        val taskEditText = findViewById<EditText>(R.id.taskEditText)

        // Manejar el clic en el botón de agregar tarea
        addButton.setOnClickListener {
            // Obtener el texto ingresado en el campo de texto
            val task = taskEditText.text.toString().trim()

            // Verificar si el texto no está vacío
            if (task.isNotEmpty()) {
                // Agregar la tarea a la lista
                tasks.add(task)

                // Notificar al adaptador sobre la nueva tarea agregada
                adapter.notifyItemInserted(tasks.size - 1)

                // Actualizar los números de las tareas en el adaptador
                adapter.updateTaskNumbers()

                // Limpiar el campo de texto
                taskEditText.text.clear()
            }
        }
    }

    // Clase interna para representar una tarea
    private data class Task(val name: String, var color: Int)

    // Clase interna para el adaptador del RecyclerView
    private class TasksAdapter(private val tasks: MutableList<String>) :
        RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
        private var selectedItemPosition = RecyclerView.NO_POSITION

        // Crea un nuevo ViewHolder para los elementos de la lista
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return TaskViewHolder(itemView)
        }

        // Enlaza los datos con el ViewHolder en la posición especificada
        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val task = tasks[position]

            // Capitalizar la primera letra de la tarea
            val capitalizedTask = task.capitalize()

            // Llamar al método "bind" del ViewHolder para enlazar los datos
            // Pasamos la posición incrementada en 1 y la tarea capitalizada
            holder.bind(position + 1, capitalizedTask)

            // Verificar si la posición actual coincide con la posición seleccionada
            if (position == selectedItemPosition) {
                // Cambiar el color de fondo del elemento de la lista seleccionado
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            } else {
                // Restaurar el color de fondo predeterminado del elemento de la lista
                holder.itemView.setBackgroundColor(Color.TRANSPARENT)
            }

            // Manejar el clic en el elemento de la lista
            holder.itemView.setOnClickListener {
                val previousSelectedPosition = selectedItemPosition
                selectedItemPosition = position

                // Notificar al adaptador sobre los cambios en los elementos seleccionados
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedItemPosition)
            }
        }

        // Devuelve el número total de elementos en la lista
        override fun getItemCount(): Int = tasks.size

        // Devuelve la posición seleccionada actualmente
        fun getSelectedPosition(): Int = selectedItemPosition

        // Limpia la selección de la tarea
        fun clearSelection() {
            val previousSelectedPosition = selectedItemPosition
            selectedItemPosition = RecyclerView.NO_POSITION

            // Notificar al adaptador sobre los cambios en los elementos seleccionados
            if (previousSelectedPosition != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelectedPosition)
            }
        }

        // Actualiza los números de las tareas en el adaptador
        fun updateTaskNumbers() {
            notifyDataSetChanged()
        }

        // Clase interna para el ViewHolder de los elementos de la lista
        private inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val taskTextView: TextView = itemView.findViewById(android.R.id.text1)

            // Enlaza los datos con el ViewHolder
            fun bind(taskNumber: Int, task: String) {
                taskTextView.text = "$taskNumber. $task"
            }
        }
    }
}
