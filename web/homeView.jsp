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
    <body style="background-color: black; color:white">

        <jsp:include page="/template/_header.jsp"></jsp:include>
        <jsp:include page="/template/_menu.jsp"></jsp:include>

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


            <form method="POST" action="${pageContext.request.contextPath}/criaInfra">
            
                <text>

                <h3>    <p style="color:red">Se este for seu primeiro acesso :</p></h3>
 
                Você terá que criar toda a infraestrutura do sistema.

                Certifique-se de haver uma instância do servidor MySQL de banco de dados

                rodando em sua máquina. Para isso, abra o gerenciador de tarefas do seu 

                sistema operacional ou verifique na linha de comando se o serviço MySQL 

                foi iniciado. 

                Inicie o serviço MYSQL e, após, clique no campo abaixo: 
               
                </text>
                <input type="submit" value="CRIAR INFRAESTRUTURA"/>

        </form>


        <jsp:include page="/template/_footer.jsp"></jsp:include>

    </body>
</html>