/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Util.OperacoesBancoDados;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CriaInfraServletController", urlPatterns = {"/criaInfra"})
public class CriaInfraServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    OperacoesBancoDados fabrica = new OperacoesBancoDados();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CriaInfraServletController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CriaInfraServletController at " + request.getContextPath() + "</h1>");
            
            
            out.println("</body>");
            out.println("</html>");
            
            try {
            out.println("Criando a base de dados...");
            criarBaseDados(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServletController.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Verifique se a base já existe ...");

        }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
        // When the usuario enters usuarioName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            criarBaseDados(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CriaInfraServletController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CriaInfraServletController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void criarBaseDados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        String errorString = null;
        Connection conn = null;

        try {

            usuarioDAO.findUsuario(1);
            errorString = "A base de dados não foi criada com sucesso!";
            request.setAttribute("errorString", errorString);
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("WEB-INF/view/loginView.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.getMessage();

            fabrica.criaBaseDados();

            try {

                fabrica.criaInfraestrutura();
                errorString = "A infraestrutura foi criada com sucesso!";
                request.setAttribute("errorString", errorString);
                // Forward to /WEB-INF/views/login.jsp
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                dispatcher.forward(request, response);

            }catch (IOException | ClassNotFoundException | SQLException | ServletException multiException) {
                e.printStackTrace();
                errorString = "A infraestrutura não foi criada!";
                request.setAttribute("errorString", errorString);
                // Forward to /WEB-INF/views/login.jsp
                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                dispatcher.forward(request, response);

            } finally {

                fabrica.fecharConexao(conn);

            }

        }
    }
}

    