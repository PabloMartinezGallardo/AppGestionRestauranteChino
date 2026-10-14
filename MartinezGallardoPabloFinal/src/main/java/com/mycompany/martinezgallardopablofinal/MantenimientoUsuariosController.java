/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.martinezgallardopablofinal;

import clasesJava.Cliente;
import clasesJava.Empleado;
import clasesJava.Producto;
import clasesJava.Venta;
import static com.mycompany.martinezgallardopablofinal.MantenimientoClientesController.comprobarIdCliente;
import static com.mycompany.martinezgallardopablofinal.MantenimientoClientesController.darCliente;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class MantenimientoUsuariosController implements Initializable {


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
    private TableColumn<Empleado, Integer> colId;
    @FXML
    private TableColumn<Empleado, String> colDni;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colApellidos;
    @FXML
    private TableColumn<Empleado, String> colEmail;
    @FXML
    private TableColumn<Empleado, Integer> colTlf;
    @FXML
    private TableColumn<Empleado, String> colNick;
    @FXML
    private TableColumn<Empleado, String> colPasswd;
    @FXML
    private TableColumn<Empleado, Double> colSalario;
    @FXML
    private TableColumn<Empleado, Integer> colNivel;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField campoNick;
    @FXML
    private TextField campoPasswd;
    @FXML
    private TextField campoNivel;
    @FXML
    private TextField campoSalario;
    @FXML
    private TableView<Empleado> tvEmpleados;
    
    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private static Empleado usuActivo;
    private ObservableList<Empleado> observableList;
    private boolean repetido;
    private int i;
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
        colNick.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getNick()));
        colPasswd.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getPasswd()));
        colSalario.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getSalario()));
        colNivel.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getNivel()));
        
        
        observableList = FXCollections.observableArrayList(empleados);
        
        tvEmpleados.setItems(observableList);
        
        tvEmpleados.getSelectionModel().selectedItemProperty().addListener(
     (observable, oldValue, personaSeleccionada) -> {

        if (personaSeleccionada != null) {
            int indice=tvEmpleados.getSelectionModel().getSelectedIndex();
            campoId.setText(String.valueOf(personaSeleccionada.getId()));
            campoNombre.setText(personaSeleccionada.getNombre());
            campoApellidos.setText(personaSeleccionada.getApellidos());
            campoDni.setText(personaSeleccionada.getNombre());
            campoEmail.setText(personaSeleccionada.getApellidos());
            campoTlf.setText(String.valueOf(personaSeleccionada.getTlf()));
            campoNick.setText(personaSeleccionada.getNick());
            campoPasswd.setText(personaSeleccionada.getPasswd());
            campoSalario.setText(String.valueOf(personaSeleccionada.getSalario()));
            campoNivel.setText(String.valueOf(personaSeleccionada.getNivel()));
            
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
            repetido = comprobarIdEmpleado(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                FuncionesGraficas.warning("Advertencia", "Ese empleado ya existe");
                
            }else{
                Empleado nuevo = new Empleado(Integer.parseInt(campoId.getText()), campoNick.getText(), campoPasswd.getText(), Integer.parseInt(campoNivel.getText()), Double.parseDouble(campoSalario.getText()), campoDni.getText(), campoNombre.getText(), campoApellidos.getText(), campoEmail.getText(), Integer.parseInt(campoTlf.getText()));
                empleados.add(nuevo);
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
                Empleado seleccionado = tvEmpleados.getSelectionModel().getSelectedItem();
                
                if(seleccionado == null){
                    FuncionesGraficas.warning("Advertencia", "Selecciona un empleado primero");
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dar de baja");
                    alert.setHeaderText("¿Seguro que desea eliminar a este empleado?");
                    alert.setContentText("Se elminará a " + seleccionado.getNombre() + " " + seleccionado.getApellidos() + " permanentemente.");
                    alert.showAndWait().ifPresent(respuesta -> {
                        if (respuesta == ButtonType.OK) {
                            empleados.remove(seleccionado);
                            tvEmpleados.setItems(FXCollections.observableArrayList(empleados));
                            
                            FuncionesGraficas.mostrarDatos("Éxito!", "Se ha dado de baja correctamente");
                        }
                    });
                }
                
            }else{
                FuncionesGraficas.warning("Advertencia", "El empleado con ese ID no existe");
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
                i = darEmpleado(Integer.parseInt(campoId.getText()));
                
                empleados.get(i).setDni(campoDni.getText());
                empleados.get(i).setNombre(campoNombre.getText());
                empleados.get(i).setApellidos(campoApellidos.getText());
                empleados.get(i).setTlf(Integer.parseInt(campoTlf.getText()));
                empleados.get(i).setEmail(campoEmail.getText());
                empleados.get(i).setNick(campoNick.getText());
                empleados.get(i).setPasswd(campoPasswd.getText());
                empleados.get(i).setSalario(Double.parseDouble(campoSalario.getText()));
                
                FuncionesGraficas.mostrarDatos("Éxito!", "Se ha modificado correctamente");
                
                tvEmpleados.setItems(FXCollections.observableArrayList(empleados));
                
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
        campoNick.setText("");
        campoPasswd.setText("");
        campoSalario.setText("");
        campoNivel.setText("");
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
    }
    
    
    public static boolean comprobarIdEmpleado(int id){
        for(int i=0; i<empleados.size(); i++)
            if(id == empleados.get(i).getId())
                return true;
        
        return false;
    }
    
    
    public static int darEmpleado(int id){
        for(int i=0; i<empleados.size(); i++)
            if(id == empleados.get(i).getId())
                return i;
        
        return -1;
    }

}
