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
<link rel="stylesheet" href="styles/view.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<body>
<form action="registroservico" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Registrar Serviço</legend>

        <label class="formLabel" for="inputName" >Nome</label>
        <input class="formInput" type="text" id="inputName" required minlength="3" name="nome">

        <label class="formLabel" for="inputDescricao">Descrição</label>
        <input class="formInput" type="text" id="inputDescricao" required minlength="5" maxlength="200" name="descricao">

        <label class="formLabel" for="inputValor">Valor</label>
        <input class="formInput" type="text" id="inputValor" required minlength="4" name="valor">

        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Registrar">
            </div>
            <span class="secundaryLinkSpan"><a class="secundaryLink" href="home.jsp">Voltar</a></span>
        </div>
    </fieldset>
</form>
</body>