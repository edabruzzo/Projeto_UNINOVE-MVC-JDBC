<%-- 
    Document   : loginView
    Created on : 10/05/2018, 13:25:34
    Author     : Emm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
 <body style="background-color: black; color:white">

     <jsp:include page="/template/_header.jsp"></jsp:include>
      <jsp:include page="/template/_menu.jsp"></jsp:include>
 
      <h3>Login Page</h3>
      <h4><p style="color: red;">${errorString}</p></h4>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>Login</td>
               <td><input type="text" name="login" value= "${usuario.login}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value= "${usuario.password}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Login" />
                  <a href="${pageContext.request.contextPath}/">Cancelar</a>
               </td>
            </tr>
         </table>
      </form>
 
      <strong>Usuário pré-cadastrado: </strong>
      <strong>LOGIN: fulano</strong>
      <strong>PASSWORD: 123</strong>
           
      <jsp:include page="/template/_footer.jsp"></jsp:include>
   </body>
</html>