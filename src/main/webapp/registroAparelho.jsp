<%--
  Created by IntelliJ IDEA.
  User: Ermeson
  Date: 28/11/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/styles.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<body>
<form action="registroaparelho" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Registrar Aparelho</legend>

        <label class="formLabel" for="inputName" >Nome</label>
        <input class="formInput" type="text" id="inputName" required minlength="1" name="nome">

        <label class="formLabel" for="inputModelo">Modelo</label>
        <input class="formInput" type="text" id="inputModelo" required minlength="1" name="modelo">

        <label class="formLabel" for="inputMarca">Marca</label>
        <input class="formInput" type="text" id="inputMarca" required minlength="1" name="marca">

        <label class="formLabel" for="inputNumeroSerie">Numero de serie</label>
        <input class="formInput" type="text" id="inputNumeroSerie" required minlength="1" name="numerodeserie">

        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Registrar">
            </div>
            <span class="secundaryLinkSpan"><a class="secundaryLink" href="index.jsp">Voltar</a></span>
        </div>
    </fieldset>
</form>
</body>