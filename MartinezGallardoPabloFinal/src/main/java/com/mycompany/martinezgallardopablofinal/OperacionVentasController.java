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
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class OperacionVentasController implements Initializable {


    @FXML
    private TextField campoIdVenta;
    @FXML
    private TextField campoIdEmpleado;
    @FXML
    private TextField campoIdCliente;
    @FXML
    private TextField campoIdProducto;
    @FXML
    private Button btnVender;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Venta> tvVenta;
    @FXML
    private TableColumn<Venta, Integer> colIdVenta;
    @FXML
    private TableColumn<Venta, Producto> colProducto;
    @FXML
    private TableColumn<Venta, Cliente> colCliente;
    @FXML
    private TableColumn<Venta, Empleado> colEmpleado;
    @FXML
    private TableColumn<Venta, LocalDate> colFecha;
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
    private ObservableList<Venta> observableList;
    private int i;
    private Cliente c;
    private Empleado e;
    private Producto p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colIdVenta.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getId()));
        colProducto.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getProducto().getNombre()));
        colCliente.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getCliente().getNombre() + " " + cellData.getValue().getCliente().getApellidos()));
        colEmpleado.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getEmpleado().getNombre() + " " + cellData.getValue().getEmpleado().getApellidos()));
        colFecha.setCellValueFactory(cellData->new javafx.beans.property.SimpleObjectProperty(cellData.getValue().getFechaVenta()));
        
        
        observableList = FXCollections.observableArrayList(ventas);
        
        tvVenta.setItems(observableList);
        
        tvVenta.getSelectionModel().selectedItemProperty().addListener(
     (observable, oldValue, personaSeleccionada) -> {

        if (personaSeleccionada != null) {
            int indice=tvVenta.getSelectionModel().getSelectedIndex();
            campoIdVenta.setText(String.valueOf(personaSeleccionada.getId()));
            campoIdProducto.setText(String.valueOf(personaSeleccionada.getProducto().getId()));
            campoIdCliente.setText(String.valueOf(personaSeleccionada.getCliente().getId()));
            campoIdEmpleado.setText(String.valueOf(personaSeleccionada.getEmpleado().getId()));
            
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
    private void venderProducto(ActionEvent event) {
        try {
            i = comprobarStock(Integer.parseInt(campoIdProducto.getText()));
            if(i<=0){
                FuncionesGraficas.warning("Advertencia", "No queda stock de ese producto");
                
            }else{
                c = darCliente(Integer.parseInt(campoIdCliente.getText()));
                e = darEmpleado(Integer.parseInt(campoIdEmpleado.getText()));
                p = darProducto(Integer.parseInt(campoIdProducto.getText()));
                
                Venta nuevo = new Venta(Integer.parseInt(campoIdVenta.getText()), c, e, p, LocalDate.now());
                ventas.add(nuevo);
                p.setStock(p.getStock() - 1);
                observableList.add(nuevo);
                
                FuncionesGraficas.mostrarDatos("Éxito!", "Se ha vendido correctamente");
            }
            
        } catch (IllegalArgumentException e) {
            FuncionesGraficas.warning("Advertencia", "Error al vender el producto");
        }
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        campoIdCliente.setText("");
        campoIdEmpleado.setText("");
        campoIdProducto.setText("");
        campoIdVenta.setText("");
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) this.btnVolver.getScene().getWindow();
        stage.close();
    }
    
    
    public static int comprobarStock(int id){
        for(int i=0; i<prouctos.size(); i++){
            if(id == prouctos.get(i).getId())
                return prouctos.get(i).getStock();
        }
        
        return -1;
    }
    
    
    public static Cliente darCliente(int id){
        for(Cliente c: clientes){
            if(id == c.getId())
                return c;
        }
        
        return null;
    }
    
    
    public static Empleado darEmpleado(int id){
        for(Empleado e: empleados){
            if(id == e.getId())
                return e;
        }
        
        return null;
    }
    
    
    public static Producto darProducto(int id){
        for(Producto p: prouctos){
            if(id == p.getId())
                return p;
        }
        
        return null;
    }

}
