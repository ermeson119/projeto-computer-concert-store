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
<link rel="stylesheet" href="styles/home.css">

<c:if test="${sessionScope.usuario == null}">
    <c:redirect url="index.jsp"/>
</c:if>


<form action="registroos" method="post" class="myForm">
    <fieldset class="formField">
        <%--@declare id="inputaparelho"--%><legend class="formTitle">Registrar OS</legend>

        <label class="formLabel" for="inputCliente">Cliente</label>
        <select  class="formInput" id="inputCliente" required name="cliente">
            <option value="">Selecione um cliente</option>
            <c:forEach items="${dadosCliente}" var="cliente">
                <option value="${cliente.id}" label="${cliente.nome}"></option>
            </c:forEach>
        </select>

        <label class="formLabel" for="inputAparelho">Aparelho</label>
        <select  class="formInput" id="inputAparelho" required size="1" name="aparelho">
            <option value="">Selecione um aparelho</option>
            <c:forEach items="${dadosAparelho}" var="aparelho">
                <option value="${aparelho.id}" label="${aparelho.nome} ${aparelho.modelo} ${aparelho.marca}"
                        title="${aparelho.numeroDeSerie}"></option>
            </c:forEach>
        </select>

        <label class="formLabel" for="inputServico">Serviços</label>
        <select  class="formInput" id="inputServico" required name="servico" multiple>
            <c:forEach items="${dadosServico}" var="servico">
                <option value="${servico.id}" label="${servico.nome}"></option>
            </c:forEach>
        </select>

        <label class="formLabel" for="inputEnterDate">Data de entrada</label>
        <input class="formInput" type="date" id="inputEnterDate" required minlength="8" name="dataentrada">

        <label class="formLabel" for="inputExitDate">Data de saida</label>
        <input class="formInput" type="date" id="inputExitDate" required minlength="8" name="datasaida">


        <label class="formLabel" for="inputObservacao">Observação</label>
        <textarea class="formInput"  id="inputObservacao" maxlength="200" required minlength="2" name="observacao"></textarea>


        <div class="actionsDiv">
            <div class="buttonsDiv">
                <input class="actionButton" type="submit" value="Registrar">
            </div>
            <span class="secundaryLinkSpan"><a class="secundaryLink" href="home.jsp">Voltar</a></span>
        </div>
    </fieldset>
</form>
</body>