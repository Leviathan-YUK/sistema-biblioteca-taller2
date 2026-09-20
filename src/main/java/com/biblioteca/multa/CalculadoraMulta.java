package com.biblioteca.multa;

import java.time.LocalDate;

// Principio SOLID - Inversion de dependencias (DIP): el resto del sistema
// depende de esta abstraccion, no de una formula de calculo concreta.
// Esto permite cambiar la forma de calcular la multa sin modificar
// PrestamoService ni ninguna otra clase que la use.
public interface CalculadoraMulta {

    double calcular(LocalDate fechaEsperada, LocalDate fechaReal);
}