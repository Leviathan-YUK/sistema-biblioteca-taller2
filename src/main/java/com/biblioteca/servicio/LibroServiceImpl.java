package com.biblioteca.servicio;

import com.biblioteca.modelo.EstadoLibro;
import com.biblioteca.modelo.Libro;
import com.biblioteca.repositorio.LibroRepository;

import java.util.List;

// Principio SOLID - SRP: esta clase solo se encarga de la logica de
// negocio relacionada con libros, delegando el almacenamiento al repositorio.
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    // Principio SOLID - DIP: recibe la dependencia por constructor
    // (inyeccion de dependencias), no crea el repositorio internamente.
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public void registrarLibro(Libro libro) {
        if (libroRepository.existe(libro.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un libro con el codigo: " + libro.getCodigo());
        }
        libroRepository.guardar(libro);
    }

    @Override
    public Libro registrarLibroSimilar(String codigoOrigen, String nuevoCodigo, String nuevoTitulo) {
        Libro original = buscarPorCodigo(codigoOrigen);

        // Patron Prototype: se clona el libro original y solo se
        // modifican los datos que cambian (codigo y titulo).
        Libro copia = original.clonar();
        copia.setCodigo(nuevoCodigo);
        copia.setTitulo(nuevoTitulo);
        copia.setEstado(EstadoLibro.DISPONIBLE);

        registrarLibro(copia);
        return copia;
    }

    @Override
    public Libro buscarPorCodigo(String codigo) {
        return libroRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + codigo));
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.listarTodos();
    }

    @Override
    public void actualizarEstado(String codigo, EstadoLibro nuevoEstado) {
        Libro libro = buscarPorCodigo(codigo);
        libro.setEstado(nuevoEstado);
        libroRepository.guardar(libro);
    }
}