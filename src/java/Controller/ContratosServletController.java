/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


    
import DAO.ContratoDAO;
import Model.Contrato;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
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

 
@WebServlet(urlPatterns = { "/jdbcDependente/contratos" }, loadOnStartup = 0)
public class ContratosServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    ContratoDAO contratoDAO = new ContratoDAO();
    
    public ContratosServletController() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    Connection conn = ConexaoServletController.getConexaoGuardada(request);
       
        String errorString = null;
        List<Contrato> listaContratos = null;
        try {
            listaContratos = contratoDAO.consultaContratos(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("listaContratos", listaContratos);
         
        // Forward to /WEB-INF/views/contratosView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/contratosView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
