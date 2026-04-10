//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC

package com.valverde.controladores;

import com.valverde.DAO.peliculaDAO;
import com.valverde.modelos.pelicula;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/** Clase servlet que gestiona el mantenimiento de las peliculas */
@WebServlet("/mantenimiento")
public class mantenimientocontroller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        /** Verificar que el usuario está logueado */
        HttpSession session = req.getSession();
        if (session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        /** Obtener listado de películas y mostrar el mantenimiento */
        peliculaDAO dao = new peliculaDAO();
        List<pelicula> lista = dao.obtenerTodas();
        req.setAttribute("lista", lista);
        req.getRequestDispatcher("/mantenimiento.jsp").forward(req, resp);
    }

    /**
     * Gestiona las acciones de mantenimiento de películas (insertar, editar y borrar).
     * @param req petición del cliente
     * @param resp respuesta del servidor
     * @throws ServletException error del servlet
     * @throws IOException error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        /** Verificar que el usuario tiene su sesión iniciada */
        HttpSession session = req.getSession();
        if (session.getAttribute("usuario") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String accion = req.getParameter("accion");
        String director = req.getParameter("director");
        String titulo = req.getParameter("titulo");
        String nuevoTitulo = req.getParameter("nuevoTitulo");
        String fechaStr = req.getParameter("fecha");
        String idStr = req.getParameter("id");

        peliculaDAO dao = new peliculaDAO();
        String mensaje = "";
        String tipo = "";

        try {
            /** Inserción */
            if ("insertar".equals(accion)) {
                if (dao.insertar(director, titulo, fechaStr)) {
                    mensaje = "Película insertada correctamente";
                    tipo = "exito";
                } else {
                    mensaje = "Error al insertar la película";
                    tipo = "error";
                }
                /** Edición */
            } else if ("editar".equals(accion)) {
                int id = Integer.parseInt(idStr);
                String tituloActual = (nuevoTitulo != null && !nuevoTitulo.isEmpty()) ? nuevoTitulo : titulo;
                if (dao.editar(id, director, tituloActual, fechaStr)) {
                    mensaje = "Película editada correctamente";
                    tipo = "exito";
                } else {
                    mensaje = "Error al editar la película";
                    tipo = "error";
                }
                /** Borrado */
            } else if ("borrar".equals(accion)) {
                int id = Integer.parseInt(idStr);
                if (dao.borrar(id)) {
                    mensaje = "Película eliminada correctamente";
                    tipo = "exito";
                } else {
                    mensaje = "Error al eliminar la película";
                    tipo = "error";
                }
            }
            /** Manejo de errores */
        } catch (NumberFormatException e) {
            mensaje = "Error: ID inválido";
            tipo = "error";
            System.err.println("Error al parsear ID: " + e.getMessage());
        }

        /** Redirigir para obtener el listado actualizado */
        req.getSession().setAttribute("mensaje", mensaje);
        req.getSession().setAttribute("tipoMensaje", tipo);
        resp.sendRedirect(req.getContextPath() + "/mantenimiento");
    }
}
