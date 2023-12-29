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

<c:if test="${sessionScope.usuario != null}">
    <c:redirect url="home.jsp"/>
</c:if>

<body>
<form action="login" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle">Login</legend>
        <label for="inputEmail" class="formLabel">Usuario</label>
        <input class="formInput" type="text" id="inputEmail" required name="username" minlength="3">

        <label for="inputPassword" class="formLabel">Senha</label>
        <input class="formInput" type="password" id="inputPassword" required minlength="7" name="senha">

        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Login">
                <a class="registerLink" href="registro.jsp">Registre-se</a>
            </div>

        </div>
    </fieldset>
</form>
</body>