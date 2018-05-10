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
   <body>
      <jsp:include page="/template/_header.jsp"></jsp:include>
      <jsp:include page="/template/_menu.jsp"></jsp:include>
 
      <h3>Login Page</h3>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>Login</td>
               <td><input type="text" name="nome" value= "${usuario.nome}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value= "${usuario.password}" /> </td>
            </tr>
            <tr>
               <td>Lembrar-me</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancelar</a>
               </td>
            </tr>
         </table>
      </form>
 
      <p style="color:blue;">Usuário pré-cadastrado: </p>
          <p style="color:blue;">LOGIN: fulano</p>
           <p style="color:blue;">PASSWORD: 123</p>
           
      <jsp:include page="/template/_footer.jsp"></jsp:include>
   </body>
</html>