<%--
José Luis Valverde Gallego
Recuperación UD2 - EJERCICIO CONSULTA DIRECTORES CINE CON MVC
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>CONSULTAR DIRECTORES</title>
</head>
<body>

<h1>Consultar directores de cine</h1>

<!-- Formulario de búsqueda -->
<c:if test="${not mostrarResultados and not mostrarListaFinal}">
    <form action="${pageContext.request.contextPath}/consulta" method="get">
        <label for="director">Nombre del director:</label>
        <input type="text" id="director" name="director"
               placeholder="Ej: Jim Henson" required />
        <button type="submit">Buscar</button>
    </form>

    <a href="index.jsp"><button>Inicio</button></a>

    <c:if test="${directorNoEncontrado}">
        <p>No se encontraron películas para "<c:out value="${director}" />". Por favor, intenta con otro nombre de director.</p>
    </c:if>
</c:if>


<c:if test="${requestScope.mostrarResultados}">
    <h3>Películas de: <c:out value="${director}" /></h3>

    <c:choose>
        <c:when test="${not empty peliculas}">
            <table border="1" cellpadding="6" cellspacing="0">
                <thead>
                <tr>
                    <th>Título</th>
                    <th>Fecha de estreno</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${peliculas}">
                    <tr>
                        <td><c:out value="${p.titulo}" /></td>
                        <td><c:out value="${p.fecha}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <form action="${pageContext.request.contextPath}/consulta.jsp" method="get" style="display: inline;">
                <button type="submit">Otra consulta</button>
            </form>

            <form action="${pageContext.request.contextPath}/consulta" method="get" style="display: inline;">
                <input type="hidden" name="accion" value="finalizar" />
                <button type="submit">Finalizar</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>No se encontraron películas para este director.</p>
            <form action="${pageContext.request.contextPath}/consulta.jsp" method="get">
                <button type="submit">Nueva consulta</button>
            </form>
        </c:otherwise>
    </c:choose>
</c:if>


<c:if test="${mostrarListaFinal}">
    <h3>Directores consultados:</h3>

    <c:choose>
        <c:when test="${not empty sessionScope.directoresConsultados}">
            <ul>
                <c:forEach var="d" items="${sessionScope.directoresConsultados}">
                    <li>${d}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No se han consultado directores.</p>
        </c:otherwise>
    </c:choose>

    <form action="${pageContext.request.contextPath}/consulta" method="get">
        <input type="hidden" name="accion" value="nuevaSesion" />
        <button type="submit">Nueva sesión</button>
    </form>
</c:if>

</body>
</html>