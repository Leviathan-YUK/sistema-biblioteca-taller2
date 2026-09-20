package com.biblioteca.repositorio;

import com.biblioteca.modelo.Libro;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Implementacion simple en memoria (un Map), suficiente para el taller.
// Si mas adelante se necesita persistencia real, se crearia otra clase
// que implemente LibroRepository (por ejemplo, con una base de datos),
// sin cambiar el resto del sistema.
public class LibroRepositoryMemoria implements LibroRepository {

    private final Map<String, Libro> libros = new LinkedHashMap<>();

    @Override
    public void guardar(Libro libro) {
        libros.put(libro.getCodigo(), libro);
    }

    @Override
    public Optional<Libro> buscarPorCodigo(String codigo) {
        return Optional.ofNullable(libros.get(codigo));
    }

    @Override
    public List<Libro> listarTodos() {
        return new ArrayList<>(libros.values());
    }

    @Override
    public boolean existe(String codigo) {
        return libros.containsKey(codigo);
    }
}