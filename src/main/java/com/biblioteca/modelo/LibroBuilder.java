package com.biblioteca.modelo;

// Patron creacional Builder: permite construir un Libro paso a paso,
// exigiendo solo los datos obligatorios (codigo, titulo, autor) en el
// constructor y dejando el resto como opcional mediante metodos
public class LibroBuilder {

    // Obligatorios
    private String codigo;
    private String titulo;
    private String autor;

    // Opcionales, con valores por defecto
    private CategoriaLibro categoria;
    private String editorial;
    private Integer anioPublicacion;
    private String isbn;
    private EstadoLibro estado = EstadoLibro.DISPONIBLE;

    public LibroBuilder(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
    }

    public LibroBuilder conCategoria(CategoriaLibro categoria) {
        this.categoria = categoria;
        return this;
    }

    public LibroBuilder conEditorial(String editorial) {
        this.editorial = editorial;
        return this;
    }

    public LibroBuilder conAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
        return this;
    }

    public LibroBuilder conIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LibroBuilder conEstado(EstadoLibro estado) {
        this.estado = estado;
        return this;
    }

    public Libro construir() {
        return new Libro(codigo, titulo, autor, categoria, editorial, anioPublicacion, isbn, estado);
    }
}