package com.biblioteca.repositorio;

import com.biblioteca.modelo.EstadoPrestamo;
import com.biblioteca.modelo.Prestamo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrestamoRepositoryMemoria implements PrestamoRepository {

    private final Map<String, Prestamo> prestamos = new LinkedHashMap<>();

    @Override
    public void guardar(Prestamo prestamo) {
        prestamos.put(prestamo.getId(), prestamo);
    }

    @Override
    public Optional<Prestamo> buscarPorId(String id) {
        return Optional.ofNullable(prestamos.get(id));
    }

    @Override
    public List<Prestamo> listarTodos() {
        return new ArrayList<>(prestamos.values());
    }

    @Override
    public List<Prestamo> listarActivosPorLibro(String codigoLibro) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos.values()) {
            if (p.getLibro().getCodigo().equals(codigoLibro) && p.getEstado() == EstadoPrestamo.ACTIVO) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}