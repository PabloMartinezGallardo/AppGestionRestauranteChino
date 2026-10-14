/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.martinezgallardopablofinal;

import clasesJava.Cliente;
import clasesJava.Empleado;
import clasesJava.Producto;
import clasesJava.Venta;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import modulos.FuncionesGraficas;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class PrincipalController implements Initializable {

    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private static Empleado usuActivo;
    @FXML
    private MenuItem btnMantClientes;
    @FXML
    private MenuItem btnVentas;
    @FXML
    private MenuItem btnMantUsuarios;
    @FXML
    private MenuItem btnStockMax;
    @FXML
    private MenuItem btnStockMin;
    @FXML
    private MenuItem btnManual;
    @FXML
    private MenuItem btnGuardarDatos;
    @FXML
    private Button btnCambiarUsu;
    @FXML
    private MenuItem btnSalirApp;
    @FXML
    private TextField campoUsuActivo;
    @FXML
    private TextField campoRolUsu;
    @FXML
    private MenuItem btnCalculadora;
    @FXML
    private MenuItem btnMantProductos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        campoUsuActivo.setText(usuActivo.getNombre() + " " + usuActivo.getApellidos());
        if(usuActivo.getNivel()==0){
            campoRolUsu.setText("Administrador");
        }else campoRolUsu.setText("Camarero");
    }   
    
    
    public static void datosEntrada(Empleado usu, ArrayList<Empleado> emp, ArrayList<Producto> prod, ArrayList<Cliente> cli, ArrayList<Venta> ven, File fEmp, File fProd, File fCli, File fVen){
        usuActivo = usu;
        empleados = emp;
        prouctos = prod;
        clientes = cli;
        ventas = ven;
        fichEmpleados = fEmp;
        fichProductos = fProd;
        fichClientes = fCli;
        fichVentas = fVen;
    }

    @FXML
    private void abrirMantenimientoClientes(ActionEvent event) throws IOException {
        MantenimientoClientesController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
        App.abrirNuevaVentana("MantenimientoClientes", "Mantenimiento Clientes", event);
    }

    @FXML
    private void abrirMantenimientoProductos(ActionEvent event) throws IOException {
        MantenimientoProductosController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
        App.abrirNuevaVentana("MantenimientoProductos", "Mantenimiento Productos", event);
    }
    
    @FXML
    private void abrirVentas(ActionEvent event) throws IOException {
        OperacionVentasController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
        App.abrirNuevaVentana("OperacionVentas", "Ventas productos", event);
    }

    @FXML
    private void abrirMantenimientoUsuarios(ActionEvent event) throws IOException {
        if(usuActivo.getNivel()==0){
            MantenimientoUsuariosController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
            App.abrirNuevaVentana("MantenimientoUsuarios", "Mantenimiento Usuarios", event);
        }else FuncionesGraficas.warning("Advertencia", "No tienes los privilegios para entrar");
    }

    @FXML
    private void abrirStockMaximo(ActionEvent event) throws IOException {
        StockMaximoController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
        App.abrirNuevaVentana("StockMaximo", "Stock máximo de productos", event);
    }

    @FXML
    private void abrirStockMinimo(ActionEvent event) {
    }

    @FXML
    private void abrirManual(ActionEvent event) throws IOException {
        App.abrirNuevaVentana("Manual", "Manual", event);
    }

    @FXML
    private void abrirCalculadora(ActionEvent event) throws IOException {
        App.abrirNuevaVentana("CalculadoraGrafica", "Calculadora", event);
    }
    
    @FXML
    private void guardarDatosDisco(ActionEvent event) {
        try {
            App.guardarDatos();
            FuncionesGraficas.mostrarDatos("Éxito!", "Los datos se han guardado correctamente.");
            
        } catch (Exception e) {
            FuncionesGraficas.warning("Error", "Los datos no se pudieron guardar.\n" + e.getMessage());
        }
    }

    @FXML
    private void cambiarUsuario(ActionEvent event) {
        Stage stage = (Stage) this.btnCambiarUsu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void salirApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación salir app");
        alert.setHeaderText("¿Desea salir de la aplicación?");
        alert.setContentText("Recuerda guardar los datos antes de salir.\nLos datos no guardados se perderán permanentemente.");
        Optional<ButtonType> resultado = alert.showAndWait();
        
        if(resultado.isPresent() && resultado.get() == ButtonType.OK)
            System.exit(0);
    }

    

    
    
}
