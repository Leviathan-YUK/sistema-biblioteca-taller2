package com.biblioteca.repositorio;

import com.biblioteca.modelo.Prestamo;
import java.util.List;
import java.util.Optional;

public interface PrestamoRepository {

    void guardar(Prestamo prestamo);

    Optional<Prestamo> buscarPorId(String id);

    List<Prestamo> listarTodos();

    List<Prestamo> listarActivosPorLibro(String codigoLibro);
}