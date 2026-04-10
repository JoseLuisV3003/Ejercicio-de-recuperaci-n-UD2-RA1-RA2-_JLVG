<%--
José Luis Valverde Gallego
Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error de Login</title>
</head>
<body>

<h2 style="color:red;">¡Error de inicio de sesión!</h2>

<p>
     <p><c:out value="El usuario o la contraseña son incorrectas" /></p>
</p>

<form action="login.jsp" method="get">
    <button type="submit">Volver al Login</button>
</form>

</body>
</html>