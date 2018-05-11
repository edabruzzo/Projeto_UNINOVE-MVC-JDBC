/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emm
 */
@WebServlet(name = "ConexaoServletController", urlPatterns = {"/jdbcDependente/*"}, loadOnStartup = 0)
public class ConexaoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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