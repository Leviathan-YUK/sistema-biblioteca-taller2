package com.biblioteca.multa;

import com.biblioteca.config.ConfiguracionBiblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Implementacion concreta: calcula la multa como un porcentaje fijo
// (definido en ConfiguracionBiblioteca) por cada día de retraso.
public class CalculadoraMultaPorcentual implements CalculadoraMulta {

    @Override
    public double calcular(LocalDate fechaEsperada, LocalDate fechaReal) {
        long diasRetraso = ChronoUnit.DAYS.between(fechaEsperada, fechaReal);

        if (diasRetraso <= 0) {
            return 0.0;
        }

        double porcentaje = ConfiguracionBiblioteca.getInstancia().getPorcentajeMultaPorDia();
        return diasRetraso * porcentaje;
    }
}