/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Controller.ConexaoServletController;
import Util.OperacoesBancoDados;
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

/**
 *
 * @author Emm
 *
 *
 * REFERÊNCIA PARA ESTE PROJETO :
 * https://o7planning.org/en/10285/create-a-simple-java-web-application-using-servlet-jsp-and-jdbc#a812142
 *
 *
 * Esta classe garante que uma conexão com o banco de dados só será aberta se
 * realmente for necessário. Mas, para tanto, terei que refatorar os DAOs para
 * que a conexão venha do filtro e não da fábrica de conexão OperacoesBanco
 *
 * AINDA NÃO SEI COMO VOU USAR ESTE FILTRO JDBCFilter with url-pattern = /*
 * means that all requests of users have go through this filter. JDBCFilter will
 * check the request to ensure that it only opens JDBC connection for the
 * necessary request, eg for Servlet, avoid opening JDBC connection to common
 * requests like image, css, js, html  *
 */
//OPTANDO POR FAZER O FILTRO VIA web.xml
@WebFilter(filterName = "FiltroJDBC",
            urlPatterns = {"/jdbcDependente/*"})
public class FiltroJDBC implements Filter {

    OperacoesBancoDados fabrica = new OperacoesBancoDados();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /*
    
    
    ESTOU ASSUMINDO QUE A VERIFICAÇÃO QUE O´MÉTODO ABAIXO FAZ PARA DIZER SE UM 
    SERVLET PRECISA OU NÃO DE UMA CONEXÃO JDBC SERÁ BASEADA NA MINHA ESCOLHA DE
    QUE TODOS OS SERVLETS QUE NECESSITAM DE CONEXÃO TERÃO O URLPATTERN /jdbcDependente/*
    
    
    // Check the target of the request is a servlet?
    private boolean precisaConexaoJDBC(HttpServletRequest request) {
        System.out.println("Iniciando Filtro JDBC");
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }

        // Key: servletName.
        // Value: ServletRegistration
 /*       Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();

        // Collection of all servlet in your Webapp.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
//https://stackoverflow.com/questions/31318397/webfilter-exclude-url-pattern
//NO SERVLET /home EU FAÇO A CRIAÇÃO DA INFRAESTRUTURA 
            if (mappings.contains(urlPattern) && !"/home".equals(urlPattern)) {
         if (pathInfo.contains("view") && !urlPattern.contains("/home")){
            return true;
            }
        
        return false;
    }
    
     */

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
        //NÃO ESTOU MAIS FAZENDO ESTA CHECAGEM, QUE SERÁ FEITA através do URLPATTERN /jdbcDependente/*
//        if (this.precisaConexaoJDBC(req)) {
        System.out.println("FILTRO-JDBC - Abrindo conexão no banco de dados para: " + req.getServletPath());

        Connection conn = null;
        try {
            // Create a Connection.
            conn = this.fabrica.criaConexao();
            // Set outo commit to false.
            conn.setAutoCommit(false);

            // Store Connection object in attribute of request.
            //Estranha esta forma de usar o que uma classe oferece, sem ter que instanciar objeto
            ConexaoServletController.guardarConexao(request, conn);
            // Allow request to go forward
            // (Go to the next filter or target)
            chain.doFilter(request, response);

            // Invoke the commit() method to complete the transaction with the DB.
            /*    conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                fabrica.rollbackQuietly(conn);
                throw new ServletException();
            */} catch (ClassNotFoundException ex) {
            Logger.getLogger(FiltroJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FiltroJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                fabrica.fecharConexao(conn);
            }
        } // With commons requests (images, css, html, ..)
        // No need to open the connection.
        
    }
