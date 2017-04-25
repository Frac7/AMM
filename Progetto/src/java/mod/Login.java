/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class Login extends HttpServlet {

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
        HttpSession session = request.getSession();
        String utente = request.getParameter("user");
        String password = request.getParameter("password");
        if(utente == null || password == null)
            request.getRequestDispatcher("login.jsp").forward(request, response);
        if(utente != null)
        {
            UtentiRegistrati u = UtentiRegistratiFactory.getInstance().getUserByName(utente);
            if(u != null)
            {
                if(u.getNome().equals(utente) && u.getPassword().equals(password))
                {
                    session.setAttribute("in",true);
                    if(u.getNome() == null || u.getUrlProPic() == null || u.getCognome() == null || u.getFraseBio() == null)
                        request.getRequestDispatcher("profilo.jsp").forward(request, response);
                    else
                        request.getRequestDispatcher("bacheca.jsp").forward(request, response);
                }
                else
                {
                    session.setAttribute("in",false);
                    request.getRequestDispatcher("errore.jsp").forward(request, response);
                }
            }
            else
            {
                session.setAttribute("in",false);
                request.getRequestDispatcher("errore.jsp").forward(request, response);
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

}
