<%--
José Luis Valverde Gallego
Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Bienvenido</title>
</head>
<body>

<h2>Bienvenido <c:out value="${usuario}" /></h2>

Usuario: <c:out value="${usuario}" /><br>
Clave: <c:out value="${password}" /><br><br>

<!-- MENSAJES -->
<c:if test="${not empty mensajeExito}">
    <p><strong style="color:green;"><c:out value="${mensajeExito}" /></strong></p>
</c:if>

<c:if test="${not empty mensajeError}">
    <p><strong style="color:red;"><c:out value="${mensajeError}" /></strong></p>
</c:if>

<!-- BOTÓN MANTENIMIENTO -->
<form action="mantenimiento">
    <button type="submit">Mantenimiento</button>
</form>

<hr>

<!-- BOTÓN REGISTRAR NUEVO USUARIO -->
<button onclick="mostrarFormularioRegistro()">Registrar nuevo usuario</button>

<!-- FORMULARIO REGISTRO (oculto por defecto) -->
<div id="formularioRegistro" style="display:none;">
    <h3>Registrar nuevo usuario</h3>
    
    <form method="post" action="usuariocontroller">
        Usuario: <input type="text" name="nuevoUsuario" required><br><br>
        Clave: <input type="password" name="nuevaPassword" required><br><br>
        
        <input type="hidden" name="usuarioLogueado" value="<c:out value="${usuario}" />">
        <input type="hidden" name="passwordLogueada" value="<c:out value="${password}" />">
        
        <button type="submit" name="accion" value="registrar">Registrar</button>
        <button type="button" onclick="ocultarFormularioRegistro()">Cancelar</button>
    </form>
</div>

<hr>

<form action="login.jsp">
    <button type="submit">Logout</button>
</form>

<script>
    function mostrarFormularioRegistro() {
        document.getElementById("formularioRegistro").style.display = "block";
    }

    function ocultarFormularioRegistro() {
        document.getElementById("formularioRegistro").style.display = "none";
    }
</script>

</body>
</html>