package com.biblioteca.servicio;

import com.biblioteca.modelo.EstadoLibro;
import com.biblioteca.modelo.Libro;

import java.util.List;

public interface LibroService {

    void registrarLibro(Libro libro);

    // Usa el patron Prototype (Libro.clonar()) para crear rapidamente
    // un libro similar a uno existente, cambiando solo codigo y titulo.
    Libro registrarLibroSimilar(String codigoOrigen, String nuevoCodigo, String nuevoTitulo);

    Libro buscarPorCodigo(String codigo);

    List<Libro> listarLibros();

    void actualizarEstado(String codigo, EstadoLibro nuevoEstado);
}