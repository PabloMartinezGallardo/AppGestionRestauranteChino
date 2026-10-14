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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modulos.*;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class InicioSesionController implements Initializable {

    @FXML
    private TextField campoUsu;
    @FXML
    private PasswordField campoPasswd;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private TextField mensaje;
    @FXML
    private Button btnSalirApp;
    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private Empleado i;
    private Empleado usuActivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public static void datosEntrada(ArrayList<Empleado> emp, ArrayList<Producto> prod, ArrayList<Cliente> cli, ArrayList<Venta> ven, File fEmp, File fProd, File fCli, File fVen){
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
    private void iniciarSesion(ActionEvent event) throws IOException {
        if(campoUsu.getText().isEmpty() || campoPasswd.getText().isEmpty()){
            mensaje.setText("Usuario y/o contraseña sin poner");
            campoUsu.setText("");
            campoPasswd.setText("");
            
        }else{
           i = buscarEmpleado(campoUsu.getText());
            
           if(!campoUsu.getText().equals(i.getNick()) || !campoPasswd.getText().equals(i.getPasswd())){
                mensaje.setText("Usuario y/o contraseña incorrectos");
                campoUsu.setText("");
                campoPasswd.setText("");
            
            }else{
                if(i==null){
                   mensaje.setText("Usuario no encontrado");
                }else{
                     usuActivo = i;
                     PrincipalController.datosEntrada(usuActivo, empleados, prouctos, clientes, ventas, fichVentas, fichProductos, fichClientes, fichVentas);
                     App.abrirNuevaVentana("Principal", "Principal", event);
                }  
            }
        }
    }

    @FXML
    private void salirApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación salir app");
        alert.setHeaderText("¿Desea salir de la aplicación?");
        Optional<ButtonType> resultado = alert.showAndWait();
        
        if(resultado.isPresent() && resultado.get() == ButtonType.OK)
            System.exit(0);
    }
    
    
    public static Empleado buscarEmpleado(String usu){
        for(Empleado e: empleados){
            if(e.getNick().equals(usu))
                return e;
        }
        
        return null;
    }
}
