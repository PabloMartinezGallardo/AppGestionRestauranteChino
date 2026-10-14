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
public class Cliente extends Persona implements Serializable{
    
    private int id;

    public Cliente(int id, String dni, String nombre, String apellidos, String email, int tlf) {
        super(dni, nombre, apellidos, email, tlf);
        this.id = id;
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
    
    
}
