/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Controller.ConexaoServletController;
import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emm
 */
@WebFilter(filterName = "FiltroCookies", urlPatterns = { "/*" })
public class FiltroCookies implements Filter {
 
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    
    public FiltroCookies() {
    }
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        
 
        Usuario usuarioNaSessao = ConexaoServletController.getUsuarioLogado(session);
        // 
        if (usuarioNaSessao != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }
 
        // Connection was created in JDBCFilter.
        Connection conn = ConexaoServletController.getConexaoGuardada(request);
 
        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String nomeUsuario = ConexaoServletController.getUsuarioNomeInCookie(req);
            Usuario usuario = null;
            try {
                try {
                    if(req.getPathInfo()!="/login"){
                    usuario = usuarioDAO.findByNome(conn, nomeUsuario);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FiltroCookies.class.getName()).log(Level.SEVERE, null, ex);
                }
                ConexaoServletController.guardarUsuarioLogado(session, usuario);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Mark checked Cookies.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
 
        chain.doFilter(request, response);
    }
 
}