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
 
@WebServlet(urlPatterns = { "/jdbcDependente/criarContrato" })
public class CriarContratoServletController extends HttpServlet {
    

    private static final long serialVersionUID = 1L;
    
    ContratoDAO contratoDAO = new ContratoDAO();
 
    public CriarContratoServletController() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/criarContratoView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = ConexaoServletController.getConexaoGuardada(request);

        String objetoContrato = (String) request.getParameter("objetoContrato");
        String contratado = (String) request.getParameter("contratado");
        String active = (String) request.getParameter("ativo");
        String orcamento = (String) request.getParameter("orcamentoComprometido");
       
        int codigo = 0;
        double orcamentoComprometido = 0;
        boolean ativo = false;
        String errorString = null;
        try{
            //NECESSÁRIO FAZER A CONVERSÃO DOS VALORES QUE VÊM DA TELA
            orcamentoComprometido = Double.parseDouble(orcamento);

            if("S".equals(active)) ativo = true;
            
        } catch (Exception e) {
         
            errorString = "Não foi possível ler os dados passados!";
            System.out.println("Não foi possível fazer o parser dos valores que vieram no request");
            
        }

Contrato contrato = new Contrato(codigo, objetoContrato, orcamentoComprometido, ativo, contratado);
 
        if (errorString == null) {
            try {
                contratoDAO.criarContrato(conn, contrato);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CriarContratoServletController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("contrato", contrato);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/view/criarContratoView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/jdbcDependente/contratos");
        }
    }
 
}