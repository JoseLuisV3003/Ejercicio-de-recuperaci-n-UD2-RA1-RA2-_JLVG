package com.valverde.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MySQL.
 */
public class Conexion {

    /** Datos de conexión a la base de datos */
    private static final String URL = "jdbc:mysql://localhost:3306/cine";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    /**
     * Carga el driver de MySQL.
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("No se encontró el driver de MySQL: " + e.getMessage());
        }
    }

    /**
     * Constructor privado para evitar la creación de objetos.
     */
    private Conexion() {}

    /**
     * Obtiene una conexión a la base de datos.
     * @return conexión a la base de datos
     * @throws SQLException error de conexión
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /**
     * Cierra la conexión si no es nula.
     * @param con conexión a cerrar
     */
    public static void cerrarConexion(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}