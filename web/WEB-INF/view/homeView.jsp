<%-- 
    Document   : homeView
    Created on : 10/05/2018, 13:17:15
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <h3>Home Page</h3>
      
      <br>PROJETO DESENVOLVIDO PARA A MATÉRIA PROGRAMAÇÃO ORIENTADA A OBJETOS.<br>
      <b>Propósito do projeto:</b>
      <ul>
         <li>Login</li>
         <li>Guardar informações do usuário em cookies</li>
         <li>Gerenciamento de contratos</li>
         <li>Criação de Contrato</li>
         <li>Atualização do Contrato</li>
         <li>Remoção do Contrato</li>
      </ul>
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>