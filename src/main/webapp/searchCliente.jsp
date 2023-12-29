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
<form action="pesquisarcliente" method="post" class="myForm">
<c:if test="${dados == null}">
    <fieldset class="formField">
        <legend class="formTitle">Pesquisar Cliente</legend>

        <div class="searchContainer">
            <input class="formInput" type="text" id="searchIpt" minlength="1" name="entityinfo">
            <input type="submit" class="searchInput">
        </div>

        <div class="searchOptions">
            <label for="forid">
                <input type="radio" id="forid" name="searchtype" value="id">
                Id
            </label>

            <label for="forname">
                <input type="radio" id="forname" name="searchtype" value="nome" checked>
                Name
            </label>
        </div>
    </fieldset>
</c:if>

        <c:if test="${dados != null}">
            <form class="entityForm">
                <fieldset class="entityField">
                    <select class="entitySelect" name="entity" id="entityInput" size="5">
                        <option value="nulo" label="Voltar a pesquisar" style="color: #3a68b7;">
                            <c:forEach items="${dados}" var="cliente">
                                <option value="${cliente.id}" label="${cliente.nome}"></option>
                            </c:forEach>
                    </select>
                    <input class="entitySubmit" type="submit" value="Selecionar">
                </fieldset>
            </form>
        </c:if>

    <c:if test="${dados == null}">
        <div class="actionsDiv">
            <a class="secundaryLink" href="home.jsp">Voltar</a>
        </div>
    </c:if>
</form>
</body>