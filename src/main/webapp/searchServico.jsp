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
<form action="pesquisarservico" method="post" class="myForm">
    <fieldset class="formField">
        <legend class="formTitle" style="font-size: 2rem;">Lista de Serviços</legend>

        <form class="entityForm">
            <fieldset class="entityField">
                <select class="entitySelect" name="entity" id="entityInput" size="5">
                    <c:forEach items="${dados}" var="servico">
                        <option value="${servico.id}" label="${servico.nome}" title="${servico.descricao}"></option>
                    </c:forEach>
                </select>
                <input class="entitySubmit" type="submit" value="Selecionar">
            </fieldset>
        </form>


        <div class="actionsDiv">
            <a class="secundaryLink" href="home.jsp">Voltar</a>
        </div>
    </fieldset>
</form>
</body>