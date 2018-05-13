/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Util.OperacoesBancoDados;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emm
 */
@WebServlet(urlPatterns = {"/logout"}, loadOnStartup = 1)
public class LogoutServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LogoutServletController() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)

         request.getSession().setAttribute("usuarioLogado", null);
         Connection conn = ConexaoServletController.getConexaoGuardada(request);
         
         if(conn != null){
             
           new OperacoesBancoDados().fecharConexao(conn);             
             
         }
         
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/view/loginView.jsp");

        dispatcher.forward(request, response);

   }

    // When the usuario enters usuarioName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
