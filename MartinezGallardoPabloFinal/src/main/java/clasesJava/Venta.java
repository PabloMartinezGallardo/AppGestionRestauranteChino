/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesJava;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Venta implements Serializable{
    
    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private Producto producto;
    private LocalDate fechaVenta;

    public Venta(int id, Cliente cliente, Empleado empleado, Producto producto, LocalDate fechaVenta) {
        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.producto = producto;
        this.fechaVenta = fechaVenta;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the fechaVenta
     */
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    /**
     * @param fechaVenta the fechaVenta to set
     */
    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    
    
}
