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
 */
@WebServlet(name = "HomeServletController", urlPatterns = {"/home"})
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
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // When the usuario enters usuarioName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean hasError = false;
        String errorString = null;
        
        try {
            fabrica.criaBaseDados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Connection conn = null;
        try {
            conn = fabrica.criaConexao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        try {
            usuario = usuarioDAO.findByLoginSenha(conn, "fulano", "123");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException npe){

            if (usuario.getLogin() == null) {
                Connection connection = null;
                try {
                    conn = fabrica.criaConexao();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fabrica.criaInfraestrutura(conn);
                } catch (SQLException ex) {
                    Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    
                    if (connection != null){
                        
                        fabrica.fecharConexao(conn);
                    }
                }
            
        }else {
            
            hasError = true;
            errorString = "A base de dados já foi criada!";
            
            }
        
        }finally{
            
            if (conn!=null)   try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
        
            request.setAttribute("errorString", errorString);
           
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store usuario information in Session
        // And redirect to usuarioInfo page.
        else {
            errorString = "A base de dados foi criada com sucesso!";
            request.setAttribute("errorString", errorString);
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }
    }
 
}
