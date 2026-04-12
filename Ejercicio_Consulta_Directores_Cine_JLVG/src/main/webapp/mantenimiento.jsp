<%--
José Luis Valverde Gallego
Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.valverde.modelos.pelicula" %>
<%@ page isELIgnored="false" %>

<%
    
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }


    if (request.getAttribute("lista") == null) {
        com.valverde.DAO.peliculaDAO dao = new com.valverde.DAO.peliculaDAO();
        request.setAttribute("lista", dao.obtenerTodas());
    }
%>

<html>
<head>
    <title>Mantenimiento</title>
</head>
<body>

<h2>Mantenimiento de Películas</h2>

<c:if test="${not empty sessionScope.mensaje}">
    <p><strong style="color: <c:choose><c:when test="${sessionScope.tipoMensaje eq 'exito'}">green</c:when><c:otherwise>red</c:otherwise></c:choose>;">
        <c:out value="${sessionScope.mensaje}" />
    </strong></p>
    <% session.removeAttribute("mensaje"); session.removeAttribute("tipoMensaje"); %>
</c:if>

<h3>Películas Existentes</h3>
<table border="1">
    <tr>
        <th>Director</th>
        <th>Título</th>
        <th>Fecha</th>
        <th>Acciones</th>
    </tr>

    <c:choose>
        <c:when test="${not empty requestScope.lista}">
            <c:forEach var="p" items="${requestScope.lista}">
                <tr>
                    <td><c:out value="${p.director}" /></td>
                    <td><c:out value="${p.titulo}" /></td>
                    <td><c:out value="${p.fecha}" /></td>
                    <td>
                        <button onclick="mostrarFormularioEdicion('<c:out value="${p.id}" />', '<c:out value="${p.director}" />', '<c:out value="${p.titulo}" />', '<c:out value="${p.fecha}" />')">Editar</button>
                        <form method="post" action="${pageContext.request.contextPath}/mantenimiento" style="display:inline;">
                            <input type="hidden" name="id" value="<c:out value="${p.id}" />">
                            <button type="submit" name="accion" value="borrar" onclick="return confirm('¿Estás seguro de que quieres eliminar esta película?');">Borrar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="4" style="text-align:center;">No hay películas registradas</td>
            </tr>
        </c:otherwise>
    </c:choose>

</table>

<hr>

<div id="formularioEdicion" style="display:none;">
    <h3>Editar Película</h3>
    
    <form method="post" action="${pageContext.request.contextPath}/mantenimiento">
        Título: <input type="text" id="tituloEdit" name="nuevoTitulo"><br><br>
        Director: <input type="text" id="directorEdit" name="director" required><br><br>
        Fecha: <input type="datetime-local" id="fechaEdit" name="fecha" required><br><br>
        
        <input type="hidden" id="idEdit" name="id">
        <input type="hidden" id="tituloOriginal" name="titulo">
        
        <button type="submit" name="accion" value="editar">Guardar Cambios</button>
        <button type="button" onclick="ocultarFormularioEdicion()">Cancelar</button>
    </form>
</div>

<hr>

<h3>Añadir nueva película</h3>

<form method="post" action="${pageContext.request.contextPath}/mantenimiento">
    Director: <input type="text" name="director"><br><br>
    Título: <input type="text" name="titulo"><br><br>
    Fecha: <input type="datetime-local" name="fecha"><br><br>

    <button type="submit" name="accion" value="insertar">Insertar</button>
</form>

<script>
    function mostrarFormularioEdicion(id, director, titulo, fecha) {
        document.getElementById("idEdit").value = id;
        document.getElementById("directorEdit").value = director;
        document.getElementById("tituloEdit").value = titulo;
        document.getElementById("tituloOriginal").value = titulo;
        document.getElementById("fechaEdit").value = fecha;
        document.getElementById("formularioEdicion").style.display = "block";
    }

    function ocultarFormularioEdicion() {
        document.getElementById("formularioEdicion").style.display = "none";
    }
</script>

</body>
</html>
