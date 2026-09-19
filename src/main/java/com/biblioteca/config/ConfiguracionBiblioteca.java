package com.biblioteca.config;

// Patron creacional Singleton: garantiza una unica instancia de la
// configuracion general de la biblioteca, accesible desde cualquier
// parte del sistema sin pasarla por parametro a cada clase.
public class ConfiguracionBiblioteca {

    // Unica instancia de la clase, compartida por toda la aplicacion
    private static ConfiguracionBiblioteca instancia;

    private String nombreBiblioteca;
    private String direccion;
    private double porcentajeMultaPorDia;

    // Constructor privado: nadie puede hacer "new ConfiguracionBiblioteca()"
    // desde fuera de esta clase, solo se accede via getInstancia()
    private ConfiguracionBiblioteca() {
        // Valores por defecto
        this.nombreBiblioteca = "Biblioteca Universitaria";
        this.direccion = "Sin definir";
        this.porcentajeMultaPorDia = 0.0;
    }

    public static ConfiguracionBiblioteca getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionBiblioteca();
        }
        return instancia;
    }

    public void setConfiguracion(String nombre, String direccion, double porcentaje) {
        this.nombreBiblioteca = nombre;
        this.direccion = direccion;
        this.porcentajeMultaPorDia = porcentaje;
    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPorcentajeMultaPorDia() {
        return porcentajeMultaPorDia;
    }
}