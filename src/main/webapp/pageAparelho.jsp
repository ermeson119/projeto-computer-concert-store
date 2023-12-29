<%--
  Created by IntelliJ IDEA.
  User: Ermeson
  Date: 28/11/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<meta charset="UTF-8">
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="logic" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="styles/styles.css">
<link rel="stylesheet" href="styles/view.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>


<jsp:useBean id="aparelho" class="Modelo.Aparelho" >
    <jsp:setProperty name="aparelho" property="id" value="${dados.id}"></jsp:setProperty>
    <jsp:setProperty name="aparelho" property="nome" value="${dados.nome}"></jsp:setProperty>
    <jsp:setProperty name="aparelho" property="modelo" value="${dados.modelo}"></jsp:setProperty>
    <jsp:setProperty name="aparelho" property="marca" value="${dados.marca}"></jsp:setProperty>
    <jsp:setProperty name="aparelho" property="numeroDeSerie" value="${dados.numeroDeSerie}"></jsp:setProperty>
</jsp:useBean>

<body>
<form action="alteraraparelho" method="post" class="myForm">
    <fieldset class="formField">
        <div class="clientViewDiv">
            <div class="imgDiv">
                <img src="images/aparelhoview.png" alt="view">
            </div>

            <div class="formDiv">
                <label class="formLabel">
                    <p>ID</p>

                    <label class=actionLabel >
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text" required minlength="1" value="${aparelho.id}" maxlength="10" name="id">
                </label>

                <label class="formLabel">
                    <p>Nome</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text" required minlength="3" maxlength="50" value="${aparelho.nome}" name="nome">
                </label>

                <label class="formLabel">
                    <p>Modelo</p>

                    <label class=actionLabel>
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text"  required minlength="8" maxlength="30" value="${aparelho.modelo}" name="modelo">
                </label>

                <label class="formLabel">
                    <p>Marca</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text" required minlength="5" maxlength="30" value="${aparelho.marca}" name="marca">
                </label>

                <label class="formLabel">
                    <p>Numero de serie</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="text" required minlength="5" maxlength="30" value="${aparelho.numeroDeSerie}" name="numerodeserie">
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

    <c:if test="${dadosOS != null}">

        <div class="osDiv">
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Cliente</th>
                    <th>Aparelho</th>
                    <th>Observação</th>
                    <th>Serviços</th>
                    <th>Data de entrada</th>
                    <th>Data de saída</th>
                    <th>Valor total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dadosOS}" var="os">
                    <tr>
                        <td>${os.id}</td>
                        <td>${os.cliente.nome}</td>

                        <td title="${os.aparelho.numeroDeSerie}">
                            ${os.aparelho.nome} <br>
                            ${os.aparelho.modelo}
                            ${os.aparelho.marca}
                        </td>
                        <td>${os.observacao}</td>
                        <td>
                            <c:forEach items="${os.getServicos()}" var="servico">
                                ${servico.nome} <br>
                            </c:forEach>
                        </td>
                        <td>${os.dataEntrada}</td>
                        <td>${os.dataSaida}</td>
                        <td>${os.valorTotal}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="8">
                        Tabela de OS
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>

    </c:if>

    <div class="actionsDiv">
        <a class="secundaryLink" href="searchAparelho.jsp">Voltar</a>
    </div>

</form>

</body>
