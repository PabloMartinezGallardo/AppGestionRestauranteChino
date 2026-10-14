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
import javafx.scene.control.CheckBox;
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
public class MantenimientoProductosController implements Initializable {


    @FXML
    private TextField campoId;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoPrecio;
    @FXML
    private TextField campoStock;
    @FXML
    private Button btnAlta;
    @FXML
    private Button btnBaja;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Producto> tvProductos;
    @FXML
    private TableColumn<Producto, Integer> colId;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TableColumn<Producto, Boolean> colDisponible;
    @FXML
    private Button btnVolver;
    @FXML
    private CheckBox chkDisponible;
    
    private static ArrayList<Empleado> empleados;
    private static ArrayList<Producto> prouctos;
    private static ArrayList<Cliente> clientes;
    private static ArrayList<Venta> ventas;
    private static File fichProductos;
    private static File fichEmpleados;
    private static File fichClientes;
    private static File fichVentas;
    private static Empleado usuActivo;
    private ObservableList<Producto> observableList;
    private boolean repetido;
    private int i;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getId()));
        colNombre.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getNombre()));
        colPrecio.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getPrecio()));
        colStock.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getStock()));
        colDisponible.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().isDisponible()));
        
        
        
        observableList = FXCollections.observableArrayList(prouctos);
        
        tvProductos.setItems(observableList);
        
        tvProductos.getSelectionModel().selectedItemProperty().addListener(
     (observable, oldValue, personaSeleccionada) -> {

        if (personaSeleccionada != null) {
            int indice=tvProductos.getSelectionModel().getSelectedIndex();
            campoId.setText(String.valueOf(personaSeleccionada.getId()));
            campoNombre.setText(personaSeleccionada.getNombre());
            campoPrecio.setText(String.valueOf(personaSeleccionada.getPrecio()));
            campoStock.setText(String.valueOf(personaSeleccionada.getStock()));
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
            repetido = comprobarIdProducto(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                FuncionesGraficas.warning("Advertencia", "Ese producto ya existe");
                
            }else{
                Producto nuevo = new Producto(Integer.parseInt(campoId.getText()), campoNombre.getText(), Double.parseDouble(campoPrecio.getText()), chkDisponible.isSelected(), Integer.parseInt(campoStock.getText()));
                prouctos.add(nuevo);
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
            repetido = comprobarIdProducto(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                Producto seleccionado = tvProductos.getSelectionModel().getSelectedItem();
                
                if(seleccionado == null){
                    FuncionesGraficas.warning("Advertencia", "Selecciona un producto primero");
                    
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Dar de baja");
                    alert.setHeaderText("¿Seguro que desea eliminar a este producto?");
                    alert.setContentText("Se elminará a " + seleccionado.getNombre() + " permanentemente.");
                    alert.showAndWait().ifPresent(respuesta -> {
                        if (respuesta == ButtonType.OK) {
                            prouctos.remove(seleccionado);
                            tvProductos.setItems(FXCollections.observableArrayList(prouctos));
                            
                            FuncionesGraficas.mostrarDatos("Éxito!", "Se ha dado de baja correctamente");
                        }
                    });
                }
                
            }else{
                FuncionesGraficas.warning("Advertencia", "El producto con ese ID no existe");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al dar de baja");
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        try {
            repetido = comprobarIdProducto(Integer.parseInt(campoId.getText()));
            
            if(repetido){
                i = darProducto(Integer.parseInt(campoId.getText()));
                
                prouctos.get(i).setId(Integer.parseInt(campoId.getText()));
                prouctos.get(i).setNombre(campoNombre.getText());
                prouctos.get(i).setPrecio(Double.parseDouble(campoPrecio.getText()));
                prouctos.get(i).setStock(Integer.parseInt(campoStock.getText()));
                prouctos.get(i).setDisponible(chkDisponible.isSelected());
                
                FuncionesGraficas.mostrarDatos("Éxito!", "Se ha modificado correctamente");
                
                tvProductos.setItems(FXCollections.observableArrayList(prouctos));
                
            }else{
                FuncionesGraficas.warning("Advertencia", "El producto con ese ID no existe");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al modificar");
        }
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        campoId.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
        campoStock.setText("");
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
    }

    
    public static boolean comprobarIdProducto(int id){
        for(int i=0; i<prouctos.size(); i++)
            if(id == prouctos.get(i).getId())
                return true;
        
        return false;
    }
    
    
    public static int darProducto(int id){
        for(int i=0; i<prouctos.size(); i++)
            if(id == prouctos.get(i).getId())
                return i;
        
        return -1;
    }
}
