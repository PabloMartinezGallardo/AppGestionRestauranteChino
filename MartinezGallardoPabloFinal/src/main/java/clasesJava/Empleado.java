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
public class Empleado extends Persona implements Serializable{
    
    private int id;
    private String nick;
    private String passwd;
    private int nivel;
    private double salario;

    public Empleado(int id, String nick, String passwd, int nivel, double salario, String dni, String nombre, String apellidos, String email, int tlf) {
        super(dni, nombre, apellidos, email, tlf);
        this.id = id;
        this.nick = nick;
        this.passwd = passwd;
        this.nivel = nivel;
        this.salario = salario;
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
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick the nick to set
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
