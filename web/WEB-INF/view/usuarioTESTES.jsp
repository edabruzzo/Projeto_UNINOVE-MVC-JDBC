<%-- 
    Document   : userInfoView
    Created on : 10/05/2018, 13:53:37
    Author     : Emm
--%>
 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Informações de usuários</title>
 </head>
    <body style="background-color: activeborder">

    <jsp:include page="/template/_header.jsp"></jsp:include>
    <jsp:include page="/template/_menu.jsp"></jsp:include>

    <jsp:useBean id="controll" class="Controller.UsuariosServletController"/>
    <h3>LISTA USUÁRIOS</h3>

            <p style="color: red;">${errorString}</p>

        <table border="1" cellpadding="5" cellspacing="1" >
            
            <tr>
                <th>Nome</th>
                <th>Matrícula do Funcionário</th>
                <th>Departamento</th>
                <th>Login</th>
                <th>Data de Admissão</th>
                <th>Editar</th>
                <th>Deletar</th>
            </tr>
            <c:forEach items="${listaUsuarios}" var="usuario" >
                <tr>
                    <td>${usuario.nome}</td>
                    <td>${usuario.matricula}</td>
                    <td>${usuario.departamento}</td>
                    <td>${usuario.login}</td>
                    <td>${usuario.dataAdmissao}</td>
                    <td>
                        <a href="editarContrato?code=${usuario.matricula}">Editar</a>
                    </td>
                    <td>
                        <a href="deletarContrato?code=${usuario.matricula}">Deletar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    
    
    <jsp:include page="/template/_footer.jsp"></jsp:include>
 
    
    
    
 </body>
</html>