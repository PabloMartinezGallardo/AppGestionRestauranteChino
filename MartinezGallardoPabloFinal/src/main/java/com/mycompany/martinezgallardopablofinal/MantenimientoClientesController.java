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
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modulos.FuncionesGraficas;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MantenimientoClientesController implements Initializable {

    @FXML
    private TextField campoId;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoDni;
    @FXML
    private TextField campoApellidos;
    @FXML
    private TextField campoTlf;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnBaja;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colDni;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellidos;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, Integer> colTlf;
    @FXML
    private Button btnVolver;
    @FXML
    private TableView<Cliente> tvClientes;
    
    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private static Empleado usuActivo;
    private ObservableList<Cliente> observableList;
    private boolean repetido;
    private int i;
    @FXML
    private TableColumn<?, ?> colNick;
    @FXML
    private TableColumn<?, ?> colPasswd;
    @FXML
    private TableColumn<?, ?> colSalario;
    @FXML
    private TableColumn<?, ?> colNivel;
    @FXML
    private TextField campoNick;
    @FXML
    private TextField campoPasswd;
    @FXML
    private TextField campoNivel;
    @FXML
    private TextField campoSalario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colApellidos.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getApellidos()));
        colNombre.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getNombre()));
        colEmail.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getEmail()));
        colDni.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getDni()));
        colId.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getId()));
        colTlf.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getTlf()));
        
        
        observableList = FXCollections.observableArrayList(clientes);
        
        tvClientes.setItems(observableList);
        
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
     (observable, oldValue, personaSeleccionada) -> {

        if (personaSeleccionada != null) {
            int indice=tvClientes.getSelectionModel().getSelectedIndex();
            campoId.setText(String.valueOf(personaSeleccionada.getId()));
            campoNombre.setText(personaSeleccionada.getNombre());
            campoApellidos.setText(personaSeleccionada.getApellidos());
            campoDni.setText(personaSeleccionada.getNombre());
            campoEmail.setText(personaSeleccionada.getApellidos());
            campoTlf.setText(String.valueOf(personaSeleccionada.getTlf()));
            
        }
    }
    );
        
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
    private void darAlta(ActionEvent event) {
        try {
            repetido = comprobarIdCliente(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                FuncionesGraficas.warning("Advertencia", "Ese cliente ya existe");
                
            }else{
                Cliente nuevo = new Cliente(Integer.parseInt(campoId.getText()), campoDni.getText(), campoNombre.getText(), campoApellidos.getText(), campoEmail.getText(), Integer.parseInt(campoTlf.getText()));
                clientes.add(nuevo);
                observableList.add(nuevo);
                
                FuncionesGraficas.mostrarDatos("Éxito!", "Se ha dado de alta correctamente");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al dar de alta");
        }
    }

    @FXML
    private void darBaja(ActionEvent event) {
        try {
            repetido = comprobarIdCliente(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                Cliente seleccionado = tvClientes.getSelectionModel().getSelectedItem();
                
                if(seleccionado == null){
                    FuncionesGraficas.warning("Advertencia", "Selecciona un cliente primero");
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dar de baja");
                    alert.setHeaderText("¿Seguro que desea eliminar a este cliente?");
                    alert.setContentText("Se elminará a " + seleccionado.getNombre() + " " + seleccionado.getApellidos() + " permanentemente.");
                    alert.showAndWait().ifPresent(respuesta -> {
                        if (respuesta == ButtonType.OK) {
                            clientes.remove(seleccionado);
                            tvClientes.setItems(FXCollections.observableArrayList(clientes));
                            
                            FuncionesGraficas.mostrarDatos("Éxito!", "Se ha dado de baja correctamente");
                        }
                    });
                }
                
            }else{
                FuncionesGraficas.warning("Advertencia", "El cliente con ese ID no existe");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al dar de baja");
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        try {
            repetido = comprobarIdCliente(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                i = darCliente(Integer.parseInt(campoId.getText()));
                
                clientes.get(i).setDni(campoDni.getText());
                clientes.get(i).setNombre(campoNombre.getText());
                clientes.get(i).setApellidos(campoApellidos.getText());
                clientes.get(i).setTlf(Integer.parseInt(campoTlf.getText()));
                clientes.get(i).setEmail(campoEmail.getText());
                
                FuncionesGraficas.mostrarDatos("Éxito!", "Se ha modificado correctamente");
                
                tvClientes.setItems(FXCollections.observableArrayList(clientes));
                
            }else{
                FuncionesGraficas.warning("Advertencia", "El cliente con ese ID no existe");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al modificar");
        }
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        campoApellidos.setText("");
        campoDni.setText("");
        campoEmail.setText("");
        campoId.setText("");
        campoNombre.setText("");
        campoTlf.setText("");
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
    }
    
    
    public static boolean comprobarIdCliente(int id){
        for(int i=0; i<clientes.size(); i++)
            if(id == clientes.get(i).getId())
                return true;
        
        return false;
    }
    
    
    public static int darCliente(int id){
        for(int i=0; i<clientes.size(); i++)
            if(id == clientes.get(i).getId())
                return i;
        
        return -1;
    }
    
}
