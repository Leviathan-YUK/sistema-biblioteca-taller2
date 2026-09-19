package com.biblioteca.modelo;

// Entidad principal del dominio. Se construye siempre mediante LibroBuilder,
// nunca con "new Libro(...)" directo desde fuera del paquete builder.
public class Libro {

    private String codigo;
    private String titulo;
    private String autor;
    private CategoriaLibro categoria;
    private String editorial;
    private Integer anioPublicacion;
    private String isbn;
    private EstadoLibro estado;

    public Libro(String codigo, String titulo, String autor, CategoriaLibro categoria,
                 String editorial, Integer anioPublicacion, String isbn, EstadoLibro estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
        this.estado = estado;
    }

    // Patron Prototype: crea una copia de este libro conservando sus
    // caracteristicas, para el caso "generar rapidamente un nuevo libro
    // conservando las caracteristicas de otro".
    public Libro clonar() {
        return new Libro(this.codigo, this.titulo, this.autor, this.categoria,
                this.editorial, this.anioPublicacion, this.isbn, this.estado);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public CategoriaLibro getCategoria() {
        return categoria;
    }

    public String getEditorial() {
        return editorial;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public void setEstado(EstadoLibro estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " [" + codigo + "] (" + estado + ")";
    }
}