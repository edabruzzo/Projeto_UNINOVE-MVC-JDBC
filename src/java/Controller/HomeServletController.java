/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
import Util.OperacoesBancoDados;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emm
 *
 *
 * ESTA SERVLET NÃO PODE PASSAR PELO FILTRO JDBC
 *
 * https://stackoverflow.com/questions/31318397/webfilter-exclude-url-pattern
 *
 *
 *
 */
@WebServlet(name = "HomeServletController", urlPatterns = {"/*"}, loadOnStartup = 1)
public class HomeServletController extends HttpServlet {

    OperacoesBancoDados fabrica = new OperacoesBancoDados();

    private static final long serialVersionUID = 1L;

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public HomeServletController() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        // if category page is requested
        if (!userPath.equals("/jdbcDependente/*")) {
            // TODO: Implement category request
            // use RequestDispatcher to forward request internally
            String url = "home.jsp";

            try {

                this.criarBaseDados(request, response);
                url = "WEB-INF/view/login.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
                request.getRequestDispatcher(url).forward(request, response);
                

            }

        }

    }

    // When the usuario enters usuarioName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            criarBaseDados(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void criarBaseDados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        boolean hasError = false;
        
        String errorString = null;
        
        fabrica.criaBaseDados();
        
        Connection conn = null;
        
        conn = fabrica.criaConexao();
        
        try{
            
        fabrica.criaInfraestrutura(conn);
  
        errorString = "A base de dados foi criada com sucesso!";
        request.setAttribute("errorString", errorString);
            // Forward to /WEB-INF/views/login.jsp
           RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        
            
        }catch(Exception e){
            
         e.printStackTrace();
         errorString = "A base de dados não foi criada com sucesso!";
         request.setAttribute("errorString", errorString);
          // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
          
        }finally{
            
          fabrica.fecharConexao(conn);

        }

    }
    
}
