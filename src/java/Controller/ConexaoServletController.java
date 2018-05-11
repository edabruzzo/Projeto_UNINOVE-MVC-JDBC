/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emm
 */
@WebServlet(name = "ConexaoServletController", urlPatterns = {"/jdbcDependente/*"}/*, loadOnStartup = 0*/)
public class ConexaoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    
    
    public static final String ATRIBUTO_CONEXAO = "ATRIBUTO_PARA_CONEXÃO";
 
    private static final String ATRIBUTO_NOME_USUARIO = "ATRIBUTO_PARA_GUARDAR_NOME_USUARIO_NO_COOKIE";
 
    // Store Connection in request attribute.
    // (Information guardard only exist during requests)
    public static void guardarConexao(ServletRequest request, Connection conn) {
        request.setAttribute(ATRIBUTO_CONEXAO, conn);
    }
 
    // Get the Connection object has been guardard in attribute of the request.
    public static Connection getConexaoGuardada(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATRIBUTO_CONEXAO);
        return conn;
    }
    
    

    // Guarda usuario info in Session.
    public static void guardarUsuarioLogado(HttpSession session, Usuario usuarioLogado) {
        // On the JSP can access via ${usuarioLogado}
        session.setAttribute("usuarioLogado", usuarioLogado);
    }
 
    // Get the usuario information stored in the session.
    public static Usuario getUsuarioLogado(HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        return usuarioLogado;
    }
 
 
}