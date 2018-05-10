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

@WebServlet(urlPatterns = { "jdbcDependente/editarContrato" }, loadOnStartup = 0)
public class EditarContratoServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    ContratoDAO contratoDAO = new ContratoDAO();
    
    
    public EditarContratoServletController() {
        super();
    }
 
    // Show contrato edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = ConexaoServletController.getConexaoGuardada(request);
        
        //NECESSÁRIO FAZER O PARSER DO PARÂMETRO
        String codigoStrg = (String) request.getParameter("codigo");
        int codigo = 0;
        codigo = Integer.parseInt(codigoStrg);
 
        Contrato contrato = null;
 
        String errorString = null;
 
        try {
            contrato = contratoDAO.findByCodigo(conn, codigo);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarContratoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        // If no error.
        // The contrato does not exist to edit.
        // Redirect to contratos page.
        if (errorString != null && contrato == null) {
            response.sendRedirect(request.getServletPath() + "/jdbcDependente/contratos");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("contrato", contrato);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editarContratoView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the contrato information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       Connection conn = ConexaoServletController.getConexaoGuardada(request);
 
        String code = (String) request.getParameter("codigo");
        String objetoContrato = (String) request.getParameter("objeto");
        String contratado = (String) request.getParameter("contratado");
        String active = (String) request.getParameter("ativo");
        String orcamento = (String) request.getParameter("price");
       
        int codigo = 0;
        double orcamentoComprometido = 0;
        boolean ativo = false;
        try{
            //NECESSÁRIO FAZER A CONVERSÃO DOS VALORES QUE VÊM DA TELA
            codigo = Integer.parseInt(code);
            ativo = Boolean.parseBoolean(active);
            orcamentoComprometido = Double.parseDouble(orcamento);
            
        } catch (Exception e) {
            
            System.out.println("Não foi possível fazer o parser dos valores que vieram no request");
            
        }

    Contrato contrato = new Contrato(codigo, objetoContrato, orcamentoComprometido, ativo, contratado);
  
        String errorString = null;
 
        try {
            contratoDAO.editarContrato(conn, contrato);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarContratoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("contrato", contrato);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editarContratoView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the contrato listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/jdbcDependente/contratos");
        }
    }
 
}