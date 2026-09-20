package com.biblioteca.servicio;

import com.biblioteca.modelo.*;
import com.biblioteca.multa.CalculadoraMulta;
import com.biblioteca.repositorio.PrestamoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PrestamoServiceImpl implements PrestamoService {

    private final LibroService libroService;
    private final PrestamoRepository prestamoRepository;
    private final CalculadoraMulta calculadoraMulta;

    // Inyeccion de dependencias por constructor (principio DIP):
    // esta clase no sabe si la multa se calcula por porcentaje o de
    // otra forma, solo conoce la interfaz CalculadoraMulta.
    public PrestamoServiceImpl(LibroService libroService, PrestamoRepository prestamoRepository,
                               CalculadoraMulta calculadoraMulta) {
        this.libroService = libroService;
        this.prestamoRepository = prestamoRepository;
        this.calculadoraMulta = calculadoraMulta;
    }

    @Override
    public Prestamo realizarPrestamo(String codigoLibro, String idUsuario, String nombreUsuario, int diasPlazo) {
        Libro libro = libroService.buscarPorCodigo(codigoLibro);

        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new IllegalStateException("El libro no esta disponible para prestamo: " + codigoLibro);
        }

        Usuario usuario = new Usuario(idUsuario, nombreUsuario, "");

        Prestamo prestamo = new Prestamo(
                UUID.randomUUID().toString(),
                libro,
                usuario,
                LocalDate.now(),
                LocalDate.now().plusDays(diasPlazo)
        );

        libroService.actualizarEstado(codigoLibro, EstadoLibro.PRESTADO);
        prestamoRepository.guardar(prestamo);
        return prestamo;
    }

    @Override
    public Prestamo realizarDevolucion(String idPrestamo) {
        Prestamo prestamo = prestamoRepository.buscarPorId(idPrestamo)
                .orElseThrow(() -> new IllegalArgumentException("Prestamo no encontrado: " + idPrestamo));

        LocalDate hoy = LocalDate.now();
        double multa = calculadoraMulta.calcular(prestamo.getFechaDevolucionEsperada(), hoy);

        EstadoPrestamo estadoFinal = multa > 0 ? EstadoPrestamo.DEVUELTO_CON_RETRASO : EstadoPrestamo.DEVUELTO;
        prestamo.registrarDevolucion(hoy, multa, estadoFinal);

        libroService.actualizarEstado(prestamo.getLibro().getCodigo(), EstadoLibro.DISPONIBLE);
        prestamoRepository.guardar(prestamo);
        return prestamo;
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.listarTodos();
    }
}