<%-- 
    Document   : header
    Created on : 10/05/2018, 13:08:12
    Author     : Emm
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>


<div style="background: darkgray; height: 90px; padding: 5px;">
  <div style="float: left">
     <h1>SISTEMA CONTROLE CONTRATOS</h1>
  </div>
   <div style="float: right; padding: 10px; text-align: right;">
      <!-- User store in session with attribute: loginedUser -->
     Usuário logado : <b>${usuarioLogado.getNome}</b>
     Data e hora : <b><%
         java.util.Date dNow = new java.util.Date( );
         SimpleDateFormat ft = 
         new SimpleDateFormat ("E dd/MM/yyyy 'às' hh:mm:ss k zzz");
         //https://www.tutorialspoint.com/jsp/jsp_handling_date.htm
         out.print( "<h4 align=\"center\">" + ft.format(dNow) + "</h4>");
      %></b>
   <br/>
     
 
  </div>
 
</div>