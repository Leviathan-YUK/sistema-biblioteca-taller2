package com.biblioteca.repositorio;

import com.biblioteca.modelo.Libro;
import java.util.List;
import java.util.Optional;

// Principio SOLID - DIP: los servicios dependeran de esta interfaz,
// no de una implementacion concreta de almacenamiento. Esto permite
// cambiar de "memoria" a una base de datos real sin tocar la logica de negocio.
public interface LibroRepository {

    void guardar(Libro libro);

    Optional<Libro> buscarPorCodigo(String codigo);

    List<Libro> listarTodos();

    boolean existe(String codigo);
}