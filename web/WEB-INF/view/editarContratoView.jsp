<%-- 
    Document   : editarContratoView
    Created on : 10/05/2018, 14:56:49
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Editar Contrato</title>
   </head>

    <body style="background-color: activeborder">
 
      <jsp:include page="/template/_header.jsp"></jsp:include>
      <jsp:include page="/template/_menu.jsp"></jsp:include>
 
      <h3>Editar Contrato</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty contrato}">
         <form method="POST" action="${pageContext.request.contextPath}/jdbcDependente/editarContrato">
            <input type="hidden" name="codigo" value="${contrato.codigo}" />
            <table border="0">
               <tr>
                  <td>Código</td>
                  <td style="color:red;">${contrato.codigo}</td>
               </tr>
               <tr>
                  <td>Objeto do Contrato</td>
                  <td><input type="text" name="objeto" value="${contrato.objeto}" /></td>
               </tr>
               <tr>
                  <td>Orçamento comprometido</td>
                  <td><input type="number" name="orcamento" value="${contrato.orcamentoComprometido}" /></td>
               </tr>
               <tr>
                  <td>Empresa/profissional contratado</td>
                  <td><input type="text" name="contratado" value="${contrato.contratado}" /></td>
               </tr>
                <tr>
                  <td>Ativo ? (tentando pegar este atributo via input type="radio")</td>
                  <input type="text" name="ativo" value="${contrato.ativo}" />
                  </td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/jdbcDependente/contratos">Cancelar</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="/template/_footer.jsp"></jsp:include>
 
   </body>
</html>