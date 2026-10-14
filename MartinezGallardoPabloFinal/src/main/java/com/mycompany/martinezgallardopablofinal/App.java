package com.mycompany.martinezgallardopablofinal;


import clasesJava.Cliente;
import clasesJava.Empleado;
import clasesJava.Producto;
import clasesJava.Venta;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import modulos.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<Producto> productos;
    private static ArrayList<Empleado> empleados;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos = new File("src/main/java/ficheros/datosProductos.dat");
    private static File fichEmpleados = new File("src/main/java/ficheros/datosEmpleados.dat");
    private static File fichClientes = new File("src/main/java/ficheros/datosClientes.dat");
    private static File fichVentas = new File("src/main/java/ficheros/datosVentas.dat");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("InicioSesion" + ".fxml"));
        scene = new Scene(fxmlLoader.load());
        
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Inicio de sesión");
        stage.show();
        
        stage.setOnCloseRequest(ev->{
            ev.consume(); 
       });
    }


    public static void main(String[] args) {
        cargarDatos();
        
        try {
            if(!fichEmpleados.exists()) fichEmpleados.createNewFile();
            if(!fichProductos.exists()) fichProductos.createNewFile();
            if(!fichClientes.exists()) fichClientes.createNewFile();
            if(!fichVentas.exists()) fichVentas.createNewFile();
        } catch (IOException e) {
            FuncionesGraficas.warning("ERROR", e.getMessage());
        }
        
        
        if(empleados == null){
            empleados = new ArrayList<Empleado>();
            empleados.add(new Empleado(0, "Admin", "1234", 0, 3000, "12345678A", "Pablo", "Martínez Gallardo", "pablo.mtz@gmail.com", 666777999));
        }
        if(productos == null) productos = new ArrayList<Producto>();
        if(clientes == null) clientes = new ArrayList<Cliente>();
        if(ventas == null) ventas = new ArrayList<Venta>();
        
       
        InicioSesionController.datosEntrada(empleados, productos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
        
        launch();
    }

    
    public static void cargarDatos(){
        try {
            empleados = (ArrayList<Empleado>) Ficheros.leerTabla(fichEmpleados);
            productos = (ArrayList<Producto>) Ficheros.leerTabla(fichProductos);
            clientes = (ArrayList<Cliente>) Ficheros.leerTabla(fichClientes);
            ventas = (ArrayList<Venta>) Ficheros.leerTabla(fichVentas);
        } catch (FileNotFoundException f) {
            FuncionesGraficas.warning("ERROR", f.getMessage());
        } catch (ClassNotFoundException c){
            FuncionesGraficas.warning("ERROR", c.getMessage());
        } catch (IOException e){
            FuncionesGraficas.warning("ERROR", e.getMessage());
        }
    }
    
    
    public static void guardarDatos(){
        try {
            Ficheros.escribirTabla(empleados, fichEmpleados);
            Ficheros.escribirTabla(productos, fichProductos);
            Ficheros.escribirTabla(clientes, fichClientes);
            Ficheros.escribirTabla(ventas, fichVentas);
        } catch (IOException e) {
            FuncionesGraficas.warning("ERROR", e.getMessage());
        }
    }
    
    
    public static void abrirNuevaVentana(String nombreFxml, String tituloVentana, ActionEvent event) throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(nombreFxml + ".fxml"));
            scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(tituloVentana);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            

            stage.setOnCloseRequest(ev->{
                ev.consume(); 
            });
    }
}