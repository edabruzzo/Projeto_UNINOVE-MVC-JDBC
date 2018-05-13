/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Emm
 */

import Controller.ConexaoServletController;
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
 
 
@WebFilter(filterName = "FiltroJDBC", urlPatterns = { "/jdbcDependente/*" })
public class FiltroJDBC implements Filter {
    
    
    OperacoesBancoDados fabrica = new OperacoesBancoDados();
    
    
 
    public FiltroJDBC() {
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
 
        // Only open connections for the special requests.
        // (For example, the path to the servlet, JSP, ..)
        // 
        // Avoid open connection for commons request.
        // (For example: image, css, javascript,... )
        // 
        
            System.out.println("FiltroJDBC - Abrindo conexão banco de dados para "
                    + "o seguinte servlet: " + req.getServletPath());
 
            Connection conn = null;
            try {
                // Create a Connection.
                conn = fabrica.criaConexao();
                
                // Set outo commit to false.
                conn.setAutoCommit(false);
 
                // Store Connection object in attribute of request.
                ConexaoServletController.guardarConexao(request, conn);
 
                // Allow request to go forward
                // (Go to the next filter or target)
                chain.doFilter(request, response);
 
            } catch (SQLException ex) {
            Logger.getLogger(FiltroJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                
                System.out.println("FILTRO-JDBC - Fechando conexão com banco de dados");
                fabrica.fecharConexao(conn);
            }
        }
 
    }
 
