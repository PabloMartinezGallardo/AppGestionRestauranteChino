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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modulos.FuncionesGraficas;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class StockMaximoController implements Initializable {

    @FXML
    private TextField campoIdProducto;
    @FXML
    private TextField campoPrecio;
    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnVolver;

    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private static Empleado usuActivo;
    private Producto p;
    @FXML
    private Button btnLimpiar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void calcular(ActionEvent event) {
        try {
            p = darProducto(Integer.parseInt(campoIdProducto.getText()));
            
            campoPrecio.setText(String.valueOf(p.getStock() * p.getPrecio()));
        } catch (Exception e) {
            FuncionesGraficas.warning("Advertencia", "Error al buscar el producto");
        }
    }
    
    @FXML
    private void limpiarCampos(ActionEvent event) {
        campoIdProducto.setText("");
        campoPrecio.setText("");
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
    }
    
    
    public static Producto darProducto(int id){
        for(Producto p: prouctos){
            if(p.getId() == id)
                return p;
        }
        
        return null;
    }

    
}
