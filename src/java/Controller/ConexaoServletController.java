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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emm
 */
@WebServlet(name = "ConexaoServletController", urlPatterns = {"/*"})
public class ConexaoServletController extends HttpServlet {

public static final String ATRIBUTO_CONEXAO = "ATRIBUTO_PARA_CONEXÃO";
 
    private static final String ATRIBUTO_NOME_USUARIO = "ATRIBUTO_PARA_GUARDAR_NOME_USUÁRIO_NO_COOKIE";
    
    private static final long serialVersionUID = 8391436750459991867L;
 
    // Store Connection in request attribute.
    // (Information guardard only exist during requests)
    public static void guardarConexao(ServletRequest request, Connection conn) {
        request.setAttribute(ATRIBUTO_CONEXAO, conn);
    }
 
    // Get the Connection object has been guardard in attribute of the request.
    public static Connection getGuardaConexao(ServletRequest request) {
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
 
    // Store info in Cookie
    public static void guardarUsuarioCookie(HttpServletResponse response, Usuario usuario) {
        System.out.println("Guardando Usuário no Cookie pelo período de 01 dia");
        Cookie cookieNomeUsuario = new Cookie(ATRIBUTO_NOME_USUARIO, usuario.getNome());
        // 1 day (Converted to seconds)
        cookieNomeUsuario.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieNomeUsuario);
    }
 
    public static String getUsuarioNomeInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATRIBUTO_NOME_USUARIO.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    // Delete cookie.
    public static void deletaUsuarioCookie(HttpServletResponse response) {
        Cookie cookieNomeUsuario = new Cookie(ATRIBUTO_NOME_USUARIO, null);
        System.out.println("Matando cookie do usuario");
        // 0 segundos (Esta sessão irá expirar)
        cookieNomeUsuario.setMaxAge(0);
        response.addCookie(cookieNomeUsuario);
    }
 
}