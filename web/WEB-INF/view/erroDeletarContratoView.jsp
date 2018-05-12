<%-- 
    Document   : erroDeletarContratoView
    Created on : 10/05/2018, 15:48:06
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Deletar Contrato</title>
 </head>
 
    <body style="background-color: activeborder">
 
    <jsp:include page="/template/_header.jsp"></jsp:include>
    <jsp:include page="/template/_menu.jsp"></jsp:include>
    
    <h3>Deletar Contrato</h3>
    
    <p style="color: red;">${errorString}</p>
    <a href="/jdbcDependente/contratos">Contratos</a>
    
    <jsp:include page="/template/_footer.jsp"></jsp:include>
    
 </body>
</html>