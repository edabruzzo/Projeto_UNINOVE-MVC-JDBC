/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emm
 *
 *
 * ESTA SERVLET NÃO PODE PASSAR PELO FILTRO JDBC
 *
 * https://stackoverflow.com/questions/31318397/webfilter-exclude-url-pattern
 *
 *
 *
 */
@WebServlet(name = "HomeServletController", urlPatterns = {"/home"}, loadOnStartup = 1)
public class HomeServletController extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public HomeServletController() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
        String userPath = request.getServletPath();

        // if category page is requested
        if (!userPath.equals("/jdbcDependente/*")) {
            // TODO: Implement category request
            // use RequestDispatcher to forward request internally
            String url = "";

            try {
                url = "WEB-INF/view/loginView.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            } catch (Exception ex) {
                
                url = "home.jsp";
                ex.printStackTrace();
                String errorString = "Não foi possível redirecionar para a página de login,"
                        + " Tente criar a infraestrutura!";
                request.setAttribute("errorString", errorString);
                request.getRequestDispatcher(url).forward(request, response);

            }

        }
*/
    }

}