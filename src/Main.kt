class Libro(val titulo: String, val autor: String, val numPaginas: Int, val calificacion: Int) {
    init {
        require(numPaginas > 0) { "El numero de paginas debe ser positivo." }
        require(calificacion in 0..10) { "La calificacion debe estar entre 0 y 10." }
    }
    override fun toString(): String {
        return "$titulo por $autor - $numPaginas páginas - Calificación: $calificacion"
    }
}

class ConjuntoLibros(val tamanioListaLibro: Int) {
    val listaLibros: Array<Libro?> = Array(tamanioListaLibro) { null }

    // Preguntar a Diego por que el metodo find retorna null y como distingue los valores nulos.
    fun agregarLibro(libro: Libro) {
        val index = listaLibros.indexOf(null)
        if (index != -1) {
            listaLibros[index] = libro
        } else println("No se ha podido añadir el libro a la lista.")
    }

    fun eliminarLibro(titulo: String? = null, autor: String? = null): Boolean {
        for (index in listaLibros.indices) {
            if (autor != null && listaLibros[index]?.autor == autor) {
                listaLibros[index] = null
                return true
            }
            if (titulo != null && listaLibros[index]?.titulo == titulo) {
                listaLibros[index] = null
                return true
            }
        }
        return false
    }

    fun mostrarLibrosXNotas() {
        val maxNotaLibros = listaLibros.filterNotNull().maxByOrNull { it.calificacion }
        val minNotaLibros = listaLibros.filterNotNull().minByOrNull { it.calificacion }

        if (maxNotaLibros != null && minNotaLibros != null) {
            println("${maxNotaLibros.titulo} es el libro con mayor calificacion con un ${maxNotaLibros.calificacion}")
            println("${minNotaLibros.titulo} es el libro con menor calificacion con un ${minNotaLibros.calificacion}")
        } else {
            println("No hay suficientes libros para la seleccion.")
        }
    }

    override fun toString(): String = listaLibros.joinToString { it.toString() }
}

fun main() {
    val libro1 = Libro("El Gran Gatsby", "F. Scott Fitzgerald", 218, 9)
    val libro2 = Libro("1984", "George Orwell", 328, 10)

    val conjunto = ConjuntoLibros(5)

    conjunto.agregarLibro(libro1)
    conjunto.agregarLibro(libro2)

    conjunto.mostrarLibrosXNotas()

    println(conjunto)

    conjunto.eliminarLibro("1984")
    println(conjunto)

    conjunto.eliminarLibro("F. Scott Fitzgerald")
    println(conjunto)
}