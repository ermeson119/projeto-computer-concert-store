<%--
  Created by IntelliJ IDEA.
  User: Ermeson
  Date: 28/11/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<meta charset="utf-8">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet" href="styles/view.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>

<jsp:useBean id="servico" class="Modelo.Servico">
    <jsp:setProperty name="servico" property="id" value="${dados.id}"></jsp:setProperty>
    <jsp:setProperty name="servico" property="nome" value="${dados.nome}"></jsp:setProperty>
    <jsp:setProperty name="servico" property="descricao" value="${dados.descricao}"></jsp:setProperty>
    <jsp:setProperty name="servico" property="valor" value="${dados.valor}"></jsp:setProperty>
</jsp:useBean>


<body>
<form action="alterarservico" method="post" class="myForm">
    <fieldset class="formField">
        <div class="clientViewDiv">
            <div class="imgDiv">
                <img src="images/servicoview.png" alt="view">
            </div>

            <div class="formDiv">
                <label class="formLabel">
                    <p>ID</p>

                    <label class=actionLabel >
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text" required minlength="1" value="${servico.id}" maxlength="50" name="id">
                </label>

                <label class="formLabel">
                    <p>Nome</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text" required minlength="3" maxlength="45" value="${servico.nome}" name="nome">
                </label>

                <label class="formLabel">
                    <p>Valor</p>

                    <label class=actionLabel>
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text" required minlength="4" maxlength="12" value="${servico.valor}" name="valor">
                </label>

                <label class="formLabel">
                    <p>Descricao</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <textarea class="formInput" type="text"  required minlength="5"  maxlength="200" name="descricao">${servico.descricao}</textarea>
                </label>

            </div>
        </div>
        <div class="crudDiv">
            <label for="update">
                <input id="update" type="submit" value="update" name="transaction">
            </label>
            <label for="delete">
                <input id="delete" type="submit" value="delete" name="transaction">
            </label>
        </div>
    </fieldset>


    <div class="actionsDiv">
        <a class="secundaryLink" href="searchServico.jsp">Voltar</a>
    </div>

</form>
</body>
