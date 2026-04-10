//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC

package com.valverde.DAO;

import com.valverde.conexion.Conexion;
import com.valverde.modelos.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Clase que gestiona la base de datos de usuarios */
public class usuarioDAO {

    /**
     * Comprueba si el usuario existe en la base de datos.
     * @param usuarioNombre nombre del usuario
     * @param password contraseña del usuario
     * @return objeto usuario si existe, null si no existe o hay error
     */
    public usuario login(String usuarioNombre, String password) {
        usuario usuarioObj = null;
        Connection con = null;

        /** Verificar si el usuario existe */
        try {
            con = Conexion.getConexion();

            String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuarioNombre);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuarioObj = new usuario(
                    rs.getString("usuario"),
                    rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(con);
        }

        return usuarioObj;
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     * @param usuarioNombre nombre del usuario
     * @param password contraseña del usuario
     * @return objeto usuario creado si se registra correctamente, null si ya existe o hay error
     */
    public usuario registrar(String usuarioNombre, String password) {
        Connection con = null;

        try {
            con = Conexion.getConexion();

            /** Verificar si el usuario ya existe */
            String sqlVerificar = "SELECT * FROM usuarios WHERE usuario=?";
            PreparedStatement psVerificar = con.prepareStatement(sqlVerificar);
            psVerificar.setString(1, usuarioNombre);
            ResultSet rs = psVerificar.executeQuery();

            if (rs.next()) {
                /** Usuario ya existe */
                return null;
            }

            /** Usuario no existe, insertar nuevo */
            String sqlInsertar = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";
            PreparedStatement psInsertar = con.prepareStatement(sqlInsertar);
            psInsertar.setString(1, usuarioNombre);
            psInsertar.setString(2, password);

            int filasAfectadas = psInsertar.executeUpdate();
            
            if (filasAfectadas > 0) {
                /** Usuario insertado correctamente */
                return login(usuarioNombre, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return null;
    }

    /**
     * Obtiene un usuario por su nombre de usuario
     * @param usuarioNombre nombre del usuario
     * @return objeto usuario si existe, null si no existe o hay error
     */
    public usuario obtenerPorNombre(String usuarioNombre) {
        usuario usuarioObj = null;
        Connection con = null;

        try {
            con = Conexion.getConexion();

            String sql = "SELECT * FROM usuarios WHERE usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuarioNombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuarioObj = new usuario(
                    rs.getString("usuario"),
                    rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(con);
        }

        return usuarioObj;
    }

    /**
     * Actualiza la contraseña de un usuario
     * @param usuarioNombre nombre del usuario
     * @param nuevaPassword nueva contraseña
     * @return true si se actualizó correctamente, false en caso contrario
     */
    public boolean actualizarPassword(String usuarioNombre, String nuevaPassword) {
        Connection con = null;

        try {
            con = Conexion.getConexion();

            String sql = "UPDATE usuarios SET password=? WHERE usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevaPassword);
            ps.setString(2, usuarioNombre);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion(con);
        }
    }

    /**
     * Elimina un usuario de la base de datos
     * @param usuarioNombre nombre del usuario
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminar(String usuarioNombre) {
        Connection con = null;

        try {
            con = Conexion.getConexion();

            String sql = "DELETE FROM usuarios WHERE usuario=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuarioNombre);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Conexion.cerrarConexion(con);
        }
    }

    /**
     * Verifica si un usuario existe
     * @param usuarioNombre nombre del usuario
     * @return true si existe, false en caso contrario
     */
    public boolean existe(String usuarioNombre) {
        return obtenerPorNombre(usuarioNombre) != null;
    }
}