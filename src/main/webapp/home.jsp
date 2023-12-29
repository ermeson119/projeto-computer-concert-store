<%--
  Created by IntelliJ IDEA.
  User: Ermeson
  Date: 28/11/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet" href="styles/home.css">


<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<html>
<body>
<div class="wrapper">
    <form action="gosearch" method="post" class="navegation">
        <div class="rowNavegation">
            <a class="imgDiv" href="searchCliente.jsp"><img src="images/client.png" alt="client"></a>
            <p>Clientes</p>
        </div>
        <div class="rowNavegation">
            <input type="submit" class="imgDiv" name="search" value="os">
            <img src="images/os.png" alt="ordem de serviço">
            <p>OS</p>
        </div>
        <div class="rowNavegation">
            <input type="submit" class="imgDiv" name="search" value="servico">
            <img src="images/servico.png" alt="aparelho">
            <p>Serviço</p>
        </div>
        <div class="rowNavegation">
            <a class="imgDiv" href="searchAparelho.jsp"><img src="images/aparelho.png" alt="aparelho"></a>
            <p>Aparelhos</p>
        </div>
    </form>
        <div class="registerNavegation">
            <p>Área de Registro</p>
            <form class="row" action="goregistro" method="post">
                <input type="submit" value="Cliente" name="gopage">
                <input type="submit" value="OS" name="gopage">
                <input type="submit" value="Servico" name="gopage">
                <input type="submit" value="Aparelho" name="gopage">
            </form>
        </div>
    <div class="footer">
        <form action="deslogar" method="post">
            <input type="submit" value="Deslogar">
        </form>
        <p class="input">${sessionScope.usuario.nome}</p>
    </div>
</div>
</body>
</html>
