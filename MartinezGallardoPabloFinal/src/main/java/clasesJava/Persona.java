/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesJava;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
abstract public class Persona implements Serializable{
    
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private int tlf;

    public Persona(String dni, String nombre, String apellidos, String email, int tlf) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.tlf = tlf;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tlf
     */
    public int getTlf() {
        return tlf;
    }

    /**
     * @param tlf the tlf to set
     */
    public void setTlf(int tlf) {
        this.tlf = tlf;
    }
    
    
}
