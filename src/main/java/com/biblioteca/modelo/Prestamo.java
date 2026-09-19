package com.biblioteca.modelo;

import java.time.LocalDate;

// Representa un prestamo de un libro a un usuario. La logica de calculo
// de multa vive fuera de esta clase (en CalculadoraMulta), aqui solo se
// guarda el resultado. Esto respeta el principio de responsabilidad unica (SRP).
public class Prestamo {

    private String id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private EstadoPrestamo estado;
    private double multaGenerada;

    public Prestamo(String id, Libro libro, Usuario usuario, LocalDate fechaPrestamo,
                    LocalDate fechaDevolucionEsperada) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.estado = EstadoPrestamo.ACTIVO;
        this.multaGenerada = 0.0;
    }

    // Actualiza el prestamo cuando el usuario devuelve el libro
    public void registrarDevolucion(LocalDate fecha, double multa, EstadoPrestamo estado) {
        this.fechaDevolucionReal = fecha;
        this.multaGenerada = multa;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public double getMultaGenerada() {
        return multaGenerada;
    }

    @Override
    public String toString() {
        return "Prestamo[" + id + "] " + libro.getTitulo() + " -> " + usuario.getNombre() + " (" + estado + ")";
    }
}