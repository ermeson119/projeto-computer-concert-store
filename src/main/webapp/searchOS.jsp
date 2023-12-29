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

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<body>
<form action="pesquisaros" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Pesquisar OS</legend>

        <div class="searchContainer">
            <input class="formInput" type="text" id="inputName" required minlength="1" name="entity" placeholder="Digite o ID de uma OS">
            <input type="submit" class="searchInput">
        </div>

        <div class="actionsDiv">
            <a class="secundaryLink" href="home.jsp">Voltar</a>
        </div>
    </fieldset>
</form>
</body>