<%--
José Luis Valverde Gallego
Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login Administrador</h2>

<form action="usuariocontroller" method="post">
    Usuario: <input type="text" name="usuario"><br><br>
    Clave: <input type="password" name="password"><br><br>
    <button type="submit">Entrar</button>
</form>

<br>
<form action="index.jsp">
    <button type="submit">Volver</button>
</form>

</body>
</html>