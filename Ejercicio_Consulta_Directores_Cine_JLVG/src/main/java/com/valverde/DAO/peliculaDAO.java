//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC

package com.valverde.DAO;

import com.valverde.conexion.Conexion;
import com.valverde.modelos.pelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Clase que gestiona la base de datos de peliculas */
public class peliculaDAO {

    /**
     * Busca películas por director.
     * @param director nombre del director
     * @return lista de películas encontradas
     */
    public List<pelicula> buscarPorDirector(String director) {
        List<pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT id, titulo, fecha FROM peliculas WHERE director = ?";

        /** Verificar si el director existe */
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, director);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                peliculas.add(new pelicula(rs.getString("titulo"), rs.getString("fecha")));
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta: " + e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }

        return peliculas;
    }

    /**
     * Obtiene todas las películas.
     * @return lista de películas
     */
    public List<pelicula> obtenerTodas() {
        List<pelicula> lista = new ArrayList<>();
        String sql = "SELECT id, director, titulo, fecha FROM peliculas";

        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new pelicula(
                        rs.getInt("id"),
                        rs.getString("director"),
                        rs.getString("titulo"),
                        rs.getString("fecha")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }

        return lista;
    }

    /**
     * Edita una película.
     * @param id id de la película
     * @param nuevoDirector nuevo director
     * @param nuevoTitulo nuevo título
     * @param nuevaFecha nueva fecha
     * @return true si se actualiza correctamente, false en caso contrario
     */
    public boolean editar(int id, String nuevoDirector, String nuevoTitulo, String nuevaFecha) {
        String sql = "UPDATE peliculas SET director = ?, titulo = ?, fecha = ? WHERE id = ?";

        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoDirector);
            ps.setString(2, nuevoTitulo);
            ps.setString(3, nuevaFecha);
            ps.setInt(4, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al editar película: " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(con);
        }
    }

    /**
     * Inserta una nueva película.
     * @param director director de la película
     * @param titulo título de la película
     * @param fecha fecha de la película
     * @return true si se inserta correctamente, false en caso contrario
     */
    public boolean insertar(String director, String titulo, String fecha) {
        String sql = "INSERT INTO peliculas (director, titulo, fecha) VALUES (?, ?, ?)";
        
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, director);
            ps.setString(2, titulo);
            ps.setString(3, fecha);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar película: " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(con);
        }
    }

    /**
     * Elimina una película.
     * @param id id de la película
     * @return true si se elimina correctamente, false en caso contrario
     */
    public boolean borrar(int id) {
        String sql = "DELETE FROM peliculas WHERE id = ?";
        
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al borrar película: " + e.getMessage());
            return false;
        } finally {
            Conexion.cerrarConexion(con);
        }
    }
}