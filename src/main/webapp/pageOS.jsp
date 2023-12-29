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
<link rel="stylesheet" href="styles/view.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>


<body>
<form action="alteraros" method="post" class="myForm">
    <fieldset class="formField">
        <div class="clientViewDiv">
            <div class="imgDiv">
                <img src="images/osview.png" alt="view">
            </div>

            <div class="formDiv">
                <label class="formLabel">
                    <p>ID</p>

                    <label class=actionLabel >
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text" required minlength="1" value="${dados.id}" maxlength="50" name="id">
                </label>

                <label class="formLabel">
                    <p>Cliente</p>

                    <label class=actionLabel >
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text" required minlength="3" value="${dados.cliente.nome}" title="(Cliente ID: ${dados.cliente.id})">
                </label>

                <label class="formLabel">
                    <p>Aparelho</p>

                    <label class=actionLabel>
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text" required minlength="4" value="${dados.aparelho.nome} ${dados.aparelho.modelo} ${dados.aparelho.marca} ${dados.aparelho.numeroDeSerie}" title="(Aparelho ID: ${dados.aparelho.id})">
                </label>

                <label class="formLabel">
                    <p>Data de entrada</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="date"  required minlength="5" value="${dados.dataEntrada}" name="dataentrada">
                </label>

                <label class="formLabel">
                    <p>Data de saída</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input class="formInput" type="date"  required minlength="5" value="${dados.dataSaida}" name="datasaida">
                </label>

                <label class="formLabel">
                    <p>Valor Total</p>

                    <label class=actionLabel >
                        <input disabled type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <input readonly class="formInput" type="text"  required minlength="5" value="${dados.valorTotal}">
                </label>

                <label class="formLabel">
                    <p>Observação</p>

                    <label class=actionLabel >
                        <input type="checkbox">
                        <span><img src="images/edit.png" alt="edit"></span>
                    </label>

                    <textarea class="formInput" required minlength="5" maxlength="200" name="observacao">${dados.observacao}</textarea>
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


    <div class="osDiv">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Serviço</th>
                <th>Descrição</th>
                <th>Valor</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dados.getServicos()}" var="servico">
                <tr>
                    <td>${servico.id}</td>
                    <td>${servico.nome}</td>
                    <td>${servico.descricao}</td>
                    <td>${servico.valor}</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    Tabela de Serviços
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

    <div class="actionsDiv">
        <a class="secundaryLink" href="searchOS.jsp">Voltar</a>
    </div>

</form>
</body>
