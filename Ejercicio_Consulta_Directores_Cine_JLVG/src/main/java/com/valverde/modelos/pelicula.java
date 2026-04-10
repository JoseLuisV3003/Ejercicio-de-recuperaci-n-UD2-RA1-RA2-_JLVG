//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
package com.valverde.modelos;

public class pelicula {

    private int id;
    private String director;
    private String titulo;
    private String fecha;

    // ✅ Constructor completo con ID
    public pelicula(int id, String director, String titulo, String fecha) {
        this.id = id;
        this.director = director;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    // ✅ Constructor sin ID (para inserciones)
    public pelicula(String director, String titulo, String fecha) {
        this.director = director;
        this.titulo = titulo;
        this.fecha = fecha;
    }

    // ✅ Constructor que ya usabas (para consultas)
    public pelicula(String titulo, String fecha) {
        this.titulo = titulo;
        this.fecha = fecha;
    }

    // Getters
    public int getId() { return id; }
    public String getDirector() { return director; }
    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
}