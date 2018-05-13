<%-- 
    Document   : header
    Created on : 10/05/2018, 13:08:12
    Author     : Emm
--%>

<%@page import="Model.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<div style="background: darkgray; height: 100px; padding: 5px;">
  <div style="float: left">
    <h1>SISTEMA CONTROLE CONTRATOS</h1>
    <h3 style="color:red">USUARIO LOGADO: ${usuarioLogado.nome}</h3>
  
  </div>
   <div style="float: right; padding: 10px; text-align: center;">
      <!-- User store in session with attribute: loginedUser -->
   <p> <h4>Data e hora : <%
         java.util.Date dNow = new java.util.Date( );
         SimpleDateFormat ft = 
         new SimpleDateFormat ("E dd/MM/yyyy 'às' k:mm:ss");
         //https://www.tutorialspoint.com/jsp/jsp_handling_date.htm
         out.print(ft.format(dNow));
      %></p></h4>
     
 
  </div>
 
</div>