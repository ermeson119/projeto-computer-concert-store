<%--
  Created by IntelliJ IDEA.
  User: Ermeson
  Date: 28/11/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<meta charset="UTF-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/styles.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<html>
<body>
<form action="registrocliente" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Registrar Cliente</legend>

        <label class="formLabel" for="inputName" >Nome Completo</label>
        <input class="formInput" type="text" id="inputName" required minlength="3" name="nomecompleto" maxlength="50">

        <label class="formLabel" for="inputTelefone" >Telefone</label>
        <input class="formInput" type="tel" id="inputTelefone" required minlength="8" name="telefone" maxlength="15">

        <label class="formLabel" for="inputEndereço">Endereço</label>
        <input class="formInput" type="text" id="inputEndereço" required minlength="5" name="endereco" maxlength="100">

        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Registrar">
            </div>
            <span class="secundaryLinkSpan"><a class="secundaryLink" href="home.jsp">Voltar</a></span>
        </div>
    </fieldset>
</form>
</body>
</html>