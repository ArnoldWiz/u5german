/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Alumno LMC
 */

public class Cliente {
    
    //se declaran los atibutos
    
    
    private int idCliente;
    private String nombre;
    private String telefono;
    private int estado;
    private String direccion;
    private String email;
    private String rfc;
    
    //constructores

    public Cliente(int idCliente, String nombre, String telefono, int estado, String direccion, String email, String rfc) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
        this.direccion = direccion;
        this.email = email;
        this.rfc = rfc;
    }

    
// getters y setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
