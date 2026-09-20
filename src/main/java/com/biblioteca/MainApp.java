package com.biblioteca;

import com.biblioteca.controlador.MainController;
import com.biblioteca.multa.CalculadoraMulta;
import com.biblioteca.multa.CalculadoraMultaPorcentual;
import com.biblioteca.repositorio.LibroRepository;
import com.biblioteca.repositorio.LibroRepositoryMemoria;
import com.biblioteca.repositorio.PrestamoRepository;
import com.biblioteca.repositorio.PrestamoRepositoryMemoria;
import com.biblioteca.servicio.LibroService;
import com.biblioteca.servicio.LibroServiceImpl;
import com.biblioteca.servicio.PrestamoService;
import com.biblioteca.servicio.PrestamoServiceImpl;
import com.biblioteca.config.ConfiguracionBiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Punto de entrada de la aplicacion JavaFX. Aqui se ensambla todo el
// sistema: se crean las implementaciones concretas y se inyectan por
// constructor en los servicios (principio DIP), en vez de que cada
// clase cree sus propias dependencias internamente.
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Configuracion general de la biblioteca (Singleton)
        ConfiguracionBiblioteca.getInstancia().setConfiguracion(
                "Biblioteca Central Universitaria",
                "Calle 123 # 45-67",
                0.5 // 0.5% del valor base por dia de retraso, valor de ejemplo
        );

        // Repositorios (implementacion en memoria)
        LibroRepository libroRepository = new LibroRepositoryMemoria();
        PrestamoRepository prestamoRepository = new PrestamoRepositoryMemoria();

        // Calculadora de multa (implementacion concreta de la interfaz)
        CalculadoraMulta calculadoraMulta = new CalculadoraMultaPorcentual();

        // Servicios, con sus dependencias inyectadas por constructor
        LibroService libroService = new LibroServiceImpl(libroRepository);
        PrestamoService prestamoService = new PrestamoServiceImpl(libroService, prestamoRepository, calculadoraMulta);

        // Carga de la vista FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Parent root = loader.load();

        // Inyeccion de dependencias en el controlador
        MainController controller = loader.getController();
        controller.setServicios(libroService, prestamoService);

        stage.setTitle("Sistema de Biblioteca Universitaria");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}