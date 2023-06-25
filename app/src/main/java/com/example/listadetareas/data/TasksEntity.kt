package com.example.listadetareas.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName


/*****
 * Proyect: Lista de tareas
 * Package: com.example.listadetareas.data
 *

 * Created by Rafael Barbeyto Torrellas on 20/06/2023 at 16:12

 * Created by Rafael Barbeyto Torrellas on 22/06/2023 at 12:27

 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Entity(
    tableName = "tasksList",
    indices = [Index(value = ["id"], unique = true)]
)
data class TasksEntity(
    @PrimaryKey(autoGenerate = true)

    @SerializedName("id") var id: Long = 0,
    @SerializedName("name")var name: String,
    @SerializedName("description")var description: String,
    @SerializedName("finish")var finish: Boolean
)

    val id: Long = 0,
    var name: String,
    var description: String = "",
    var finish: Boolean = false)

