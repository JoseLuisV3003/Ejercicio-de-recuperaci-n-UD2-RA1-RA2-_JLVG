//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC

package com.valverde.modelos;

public class usuario {
    
    private String usuario;
    private String password;
    
    // Constructor vacío
    public usuario() {
    }
    
    // Constructor con parámetros
    public usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "usuario{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
