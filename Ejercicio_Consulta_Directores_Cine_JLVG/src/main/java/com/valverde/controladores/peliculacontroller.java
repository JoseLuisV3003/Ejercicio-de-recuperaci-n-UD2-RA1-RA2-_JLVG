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
import java.util.ArrayList;
import java.util.List;

/** Clase servlet que gestiona la consulta de peliculas por director */
@WebServlet("/consulta")
public class peliculacontroller extends HttpServlet {

    /**
     * Gestiona la consulta de películas por director y la sesión
     * @param req petición del cliente
     * @param resp respuesta del servidor
     * @throws ServletException error del servlet
     * @throws IOException error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String director = req.getParameter("director");
        String accion = req.getParameter("accion");

        HttpSession session = req.getSession();

        /** Inicializar lista de directores consultados */
        if (session.getAttribute("directoresConsultados") == null) {
            session.setAttribute("directoresConsultados", new ArrayList<String>());
        }

        /** Finalizar consulta */
        if ("finalizar".equals(accion)) {
            req.setAttribute("mostrarListaFinal", true);
            req.getRequestDispatcher("/consulta.jsp").forward(req, resp);
            return;
        }

        /** Elimina la ultima sesión y crea una nueva */
        if ("nuevaSesion".equals(accion)) {
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/consulta.jsp");
            return;
        }

        /** Busca peliculas por director */
        if (director != null && !director.trim().isEmpty()) {
            peliculaDAO dao = new peliculaDAO();
            List<pelicula> peliculas = dao.buscarPorDirector(director.trim());

            req.setAttribute("peliculas", peliculas);
            req.setAttribute("director", director.trim());

            /** Director no encontrado */
            if (peliculas.isEmpty()) {
                req.setAttribute("directorNoEncontrado", true);
                req.getRequestDispatcher("/consulta.jsp").forward(req, resp);
                return;
            }

            /** Agregar director a la lista de directores consultados */
            @SuppressWarnings("unchecked")
            List<String> directoresConsultados = (List<String>) session.getAttribute("directoresConsultados");
            if (!directoresConsultados.contains(director.trim())) {
                directoresConsultados.add(director.trim());
            }

            req.setAttribute("mostrarResultados", true);
        }

        req.getRequestDispatcher("/consulta.jsp").forward(req, resp);
    }
}