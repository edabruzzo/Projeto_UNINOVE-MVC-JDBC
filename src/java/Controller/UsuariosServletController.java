/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Usuario;
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Emm
 */
@WebServlet(urlPatterns = { "/jdbcDependente/usuariosInfo" }, loadOnStartup = 0)
public class UsuariosServletController extends HttpServlet {


    private static final long serialVersionUID = 1L;
 
    public UsuariosServletController() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
        // Check User has logged on
        Usuario usuarioLogado = ConexaoServletController.getUsuarioLogado(session);
 
        // Not logged in
        if (usuarioLogado == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/jdbcDependente/login");
            return;
        }
        // Store info to the request attribute before forwarding.
        request.setAttribute("usuario", usuarioLogado);
 
        // If the usuario has logged in, then forward to the page
        // /WEB-INF/views/usuarioInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/usuariosInfoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}





