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

<c:if test="${sessionScope.usuario != null}">
    <c:redirect url="home.jsp"/>
</c:if>


<body>
<form action="registro" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Register</legend>

        <label class="formLabel" for="inputNameComplete">Nome Completo</label>
        <input class="formInput" type="text" id="inputNameComplete" required minlength="3" name="namecomplete">

        <label class="formLabel" for="inputUserName" >Usuario</label>
        <input class="formInput" type="text" id="inputUserName" required minlength="3" name="username">

        <label class="formLabel" for="inputPassword" >Senha</label>
        <input class="formInput" type="password" id="inputPassword" required minlength="7" name="senha">

        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Register">
            </div>
            <span><a class="secundaryLink" href="index.jsp">Faça login</a></span>

        </div>
    </fieldset>
</form>
</body>