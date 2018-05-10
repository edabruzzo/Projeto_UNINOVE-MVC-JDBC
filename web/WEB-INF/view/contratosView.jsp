<%-- 
    Document   : contratosView
    Created on : 10/05/2018, 14:02:08
    Author     : Emm
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>LISTA CONTRATOS</title>
    </head>
    <body>

        <jsp:include page="_header.jsp"></jsp:include>
        <jsp:include page="_menu.jsp"></jsp:include>

            <h3>LISTA CONTRATOS</h3>

            <p style="color: red;">${errorString}</p>

        <table border="1" cellpadding="5" cellspacing="1" >
            <tr>
                <th>Código</th>
                <th>Objeto do Contrato</th>
                <th>Orçamento Comprometido em R$</th>
                <th>Empresa/profissional contratado</th>
                <th>Ativo</th>
                <th>Editar</th>
                <th>Deletar</th>
            </tr>
            <c:forEach items="${listaContratos}" var="contrato" >
                <tr>
                    <td>${contrato.codigo}</td>
                    <td>${contrato.objeto}</td>
                    <td>${contrato.orcamentoComprometido}</td>
                    <td>${contrato.contratado}</td>
                    <td>${contrato.ativo}</td>
                    <td>
                        <a href="editarContrato?code=${contrato.codigo}">Editar</a>
                    </td>
                    <td>
                        <a href="deletarContrato?code=${contrato.codigo}">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <a href="criarContrato" >Criar Contrato</a>

        <jsp:include page="_footer.jsp"></jsp:include>

    </body>
</html>