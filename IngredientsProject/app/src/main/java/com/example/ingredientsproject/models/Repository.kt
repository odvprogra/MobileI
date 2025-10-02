package com.example.ingredientsproject.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


class RecetasViewModel : ViewModel() {
    private val _recetas = mutableStateListOf<Receta>()
    private val _ingredientes = mutableStateListOf<Ingrediente>()

    init {
        // Ingredientes globales
        _ingredientes.addAll(
            listOf(
                Ingrediente(1, "huevo"),
                Ingrediente(2, "mantequilla"),
                Ingrediente(3, "sal"),
                Ingrediente(4, "pimienta"),
                Ingrediente(5, "lechuga"),
                Ingrediente(6, "tomate"),
                Ingrediente(7, "pepino"),
                Ingrediente(8, "aceite"),
                Ingrediente(9, "pan"),
                Ingrediente(10, "aguacate"),
                Ingrediente(11, "limón"),
                Ingrediente(12, "arroz"),
                Ingrediente(13, "agua"),
                Ingrediente(14, "jamón"),
                Ingrediente(15, "queso"),
                Ingrediente(16, "mayonesa"),
                Ingrediente(17, "plátano"),
                Ingrediente(18, "leche"),
                Ingrediente(19, "miel"),
                Ingrediente(20, "pollo"),
                Ingrediente(21, "tortilla"),
                Ingrediente(22, "salsa de tomate")
            )
        )

        _recetas.addAll(
            listOf(
                Receta(
                    id = 1,
                    nombre = "Huevos revueltos",
                    ingredientes = listOf(1, 2, 3, 4),
                    preparacion = "Bate los huevos, derrite la mantequilla en una sartén y cocina revolviendo hasta que cuajen."
                ),
                Receta(
                    id = 2,
                    nombre = "Ensalada fresca",
                    ingredientes = listOf(5, 6, 7, 8, 3),
                    preparacion = "Lava y corta las verduras, mezcla en un bol y aliña con aceite y sal."
                ),
                Receta(
                    id = 3,
                    nombre = "Tostadas con aguacate",
                    ingredientes = listOf(9, 10, 11, 3),
                    preparacion = "Tosta el pan, machaca el aguacate con limón y sal, y úntalo sobre el pan."
                ),
                Receta(
                    id = 4,
                    nombre = "Arroz blanco",
                    ingredientes = listOf(12, 13, 3, 8),
                    preparacion = "Hierve el agua con sal y aceite, agrega el arroz y cocina a fuego bajo hasta que se absorba el agua."
                ),
                Receta(
                    id = 5,
                    nombre = "Sandwich de jamón y queso",
                    ingredientes = listOf(9, 14, 15, 16),
                    preparacion = "Unta el pan con mayonesa, agrega jamón y queso, y sirve frío o caliente."
                ),
                Receta(
                    id = 6,
                    nombre = "Batido de plátano",
                    ingredientes = listOf(17, 18, 19),
                    preparacion = "Licúa todos los ingredientes hasta obtener una mezcla homogénea."
                ),
                Receta(
                    id = 7,
                    nombre = "Pollo a la plancha",
                    ingredientes = listOf(20, 8, 3, 4),
                    preparacion = "Salpimenta el pollo y cocínalo en sartén caliente con un poco de aceite hasta dorar."
                ),
                Receta(
                    id = 8,
                    nombre = "Quesadilla rápida",
                    ingredientes = listOf(21, 15),
                    preparacion = "Pon el queso entre dos tortillas y calienta en sartén hasta que se derrita."
                ),
                Receta(
                    id = 9,
                    nombre = "Pasta con tomate",
                    ingredientes = listOf(22, 15, 3),
                    preparacion = "Cocina la pasta en agua con sal, escurre y mezcla con salsa de tomate y queso."
                )
            )
        )
    }

    fun getRecetaById(id: Int): Receta? {
        return _recetas.find { it.id == id }
    }

    fun getIngredienteById(id: Int): Ingrediente? {
        return _ingredientes.find { it.id == id }
    }

    fun getIngredientes(): List<Ingrediente> {
        return _ingredientes
    }

    fun getRecetas(): List<Receta> {
        return _recetas
    }

    fun getNombresIngredientes(ids: List<Int>): List<String> {
        return ids.mapNotNull { id -> getIngredienteById(id)?.nombre }
    }
}
