/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDAO;
import Model.Usuario;
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

/**
 *
 * @author Emm
 */
@WebServlet(urlPatterns = {"/jdbcDependente/usuariosInfo"})
public class UsuariosServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    UsuarioDAO usuarioDAO = new UsuarioDAO();


    public UsuariosServletController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        Connection conn = ConexaoServletController.getConexaoGuardada(request);

        String errorString = null;

        List<Usuario> listaUsuarios = null;
       
        try {
           listaUsuarios = usuarioDAO.consultaUsuarios(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContratosServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("listaUsuarios", listaUsuarios);

   
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/view/usuarioInfoView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    
}
