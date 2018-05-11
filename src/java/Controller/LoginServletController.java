/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
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
@WebServlet(urlPatterns = {"/login"}, loadOnStartup = 1)
public class LoginServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginServletController() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
*/
    }

    // When the usuario enters usuarioName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Usuario usuario = null;
        boolean hasError = false;
        String errorString = null;

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {

            hasError = true;
            errorString = "Login e senha obrigatórios!";
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
/*            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
*/

                HttpSession session = request.getSession();
                response.sendRedirect(request.getContextPath() + "/login");


        } else {

            try {
                usuario = usuarioDAO.findByLoginSenha(login, password);
            } catch (Exception ex) {

                hasError = true;
                errorString = "Login ou password inválido!";

                usuario = new Usuario();
                usuario.setLogin(login);
                usuario.setPassword(password);

                // Store information in request attribute, before forward.
                request.setAttribute("errorString", errorString);
                request.setAttribute("usuario", usuario);

                // Forward to /WEB-INF/views/login.jsp
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/view/loginView.jsp");

                dispatcher.forward(request, response);
            } // If no error
            // Store usuario information in Session
            // And redirect to usuarioInfo page.
            if (hasError == false && usuario != null) {

                HttpSession session = request.getSession();
                ConexaoServletController.guardarUsuarioLogado(session, usuario);
                // Redirect to usuarioInfo page.
                response.sendRedirect(request.getContextPath() + "/jdbcDependente/usuariosInfo");

            }

        }
    }
}
