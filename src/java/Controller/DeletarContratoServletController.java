/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.ContratoDAO;
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
@WebServlet(urlPatterns = { "/jdbcDependente/deletarContrato" }, loadOnStartup = 0)
public class DeletarContratoServletController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    ContratoDAO contratoDAO = new ContratoDAO();
 
    public DeletarContratoServletController() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Connection conn = ConexaoServletController.getConexaoGuardada(request);

         String codigoString = (String) request.getParameter("codigo");
 
        int codigo = 0;
        codigo = Integer.parseInt(codigoString);

        String errorString = null;
 
        try {
            contratoDAO.removerContrato(conn, codigo);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(DeletarContratoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        // If has an error, redirecte to the error page.
        if (errorString != null) {
            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            // 
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/erroDeletarContratoView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.        
        else {
            response.sendRedirect(request.getContextPath() + "/jdbcDependente/contratos");
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}