<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : usuarioTESTES
    Created on : 12/05/2018, 12:01:46
    Author     : Emm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background-color: activeborder">

        <jsp:include page="/template/_header.jsp"></jsp:include>
        <jsp:include page="/template/_menu.jsp"></jsp:include>

        <h1>PÁGINA PARA TESTE DO JSTL - FAZENDO QUERIES DIRETAMENTE NA PÁGINA</h1>

        <%
            try{
            Class.forName("com.mysql.jdbc.Driver"); 
            }catch(ClassNotFoundException c){
                out.println("Driver não encontrado");
            }
        %>

        <sql:query var="resultado" dataSource="/jdbc/projetoUNINOVE">
            SELECT * FROM tb_usuario
        </sql:query>

        <table border="1">
            <!-- column headers -->
            <tr>
                <c:forEach var="columnName" items="${resultado.columnNames}">
                    <th><c:out value="${columnName}"/></th>
                    </c:forEach>
            </tr>
            <!-- column data -->
            <c:forEach var="row" items="${resultado.rowsByIndex}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td><c:out value="${column}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>







        <jsp:include page="/template/_footer.jsp"></jsp:include>



    </body>
</html>
