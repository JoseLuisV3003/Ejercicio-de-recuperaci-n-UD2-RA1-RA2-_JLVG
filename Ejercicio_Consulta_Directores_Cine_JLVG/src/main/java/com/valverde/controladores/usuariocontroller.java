//José Luis Valverde Gallego
//Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC

package com.valverde.controladores;

import com.valverde.DAO.usuarioDAO;
import com.valverde.modelos.usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** Clase servlet que maneja el registro de usuarios*/
@WebServlet("/usuariocontroller")
public class usuariocontroller extends HttpServlet {

    /**
     * Gestiona el registro y el inicio de sesión de usuarios.
     * @param request petición del cliente
     * @param response respuesta del servidor
     * @throws ServletException error del servlet
     * @throws IOException error de entrada/salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        /** Registro de usuario */
        if ("registrar".equals(accion)) {
            String nuevoUsuario = request.getParameter("nuevoUsuario");
            String nuevaPassword = request.getParameter("nuevaPassword");
            String usuarioLogueado = request.getParameter("usuarioLogueado");
            String passwordLogueada = request.getParameter("passwordLogueada");

            /** Validar campos */
            if (nuevoUsuario != null && !nuevoUsuario.trim().isEmpty() &&
                nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {

                usuarioDAO dao = new usuarioDAO();
                usuario usuarioRegistrado = dao.registrar(nuevoUsuario.trim(), nuevaPassword.trim());

                /** Mensaje de registro */
                if (usuarioRegistrado != null) {
                    request.setAttribute("mensajeExito", "Usuario registrado correctamente.");
                } else {
                    request.setAttribute("mensajeError", "El usuario ya existe o hubo un error en el registro.");
                }
            } else {
                request.setAttribute("mensajeError", "Por favor completa todos los campos.");
            }

            request.setAttribute("usuario", usuarioLogueado);
            request.setAttribute("password", passwordLogueada);
            request.getRequestDispatcher("logged.jsp").forward(request, response);
            return;
        }

        /** Inicio de sesión*/
        String usuarioNombre = request.getParameter("usuario");
        String password = request.getParameter("password");

        usuarioDAO dao = new usuarioDAO();
        usuario usuarioObj = dao.login(usuarioNombre, password);

        if (usuarioObj != null) {
            /** Inicio de sesión correcto */
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioObj);

            request.setAttribute("usuario", usuarioObj.getUsuario());
            request.setAttribute("password", usuarioObj.getPassword());

            RequestDispatcher rd = request.getRequestDispatcher("logged.jsp");
            rd.forward(request, response);

        } else {
            /** Inicio de sesión incorrecto */
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }


    /**
     * Redirige al formulario de login.
     * @param request petición del cliente
     * @param response respuesta del servidor
     * @throws ServletException error del servlet
     * @throws IOException error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}