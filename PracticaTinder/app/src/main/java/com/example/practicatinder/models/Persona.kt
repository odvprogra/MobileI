package com.example.practicatinder.models

import java.io.Serializable

class Persona(
    val nombre: String,
    val apellido:String,
    val edad: Int,
    val fotos: List<Int>,
    val description: String
): Serializable