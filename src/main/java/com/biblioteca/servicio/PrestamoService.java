package com.biblioteca.servicio;

import com.biblioteca.modelo.Prestamo;

import java.util.List;

public interface PrestamoService {

    Prestamo realizarPrestamo(String codigoLibro, String idUsuario, String nombreUsuario, int diasPlazo);

    Prestamo realizarDevolucion(String idPrestamo);

    List<Prestamo> listarPrestamos();
}