/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

import java.util.List;
import java.util.Arrays;
import mod.Post;
import mod.PostFactory;
import mod.UtentiRegistrati;
import mod.UtentiRegistratiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class Bacheca extends HttpServlet {

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
        Object r = session.getAttribute("in");
        if(r != null)
        {
            boolean flag = (boolean)r;
            if(!flag)
                request.getRequestDispatcher("negato.jsp").forward(request, response);
            else
            {
                List<UtentiRegistrati> l = UtentiRegistratiFactory.getInstance().getUserList();
                session.setAttribute("utenti", l);
                r = session.getAttribute("user"); //utente loggato
                String z = ((UtentiRegistrati)r).getNome();
                Object s = request.getParameter("visualizza_bacheca"); //utente del quale si vuole visualizzare la bacheca
                UtentiRegistrati u;
                /*Object v = session.getAttribute("logout");
                if(v != null)
                {
                    int logout = (int)v;
                    if(logout == 1)
                    {
                        session.setAttribute("in", false);
                        session.invalidate();
                    }
                 non funge, da rivedere 
                }*/
                if(s != null)
                {
                    String t = s.toString();
                    u = UtentiRegistratiFactory.getInstance().getUserByName(t);
                } //voglio vedere la bacheca di un altro utente
                else
                {
                    u = (UtentiRegistrati)r;
                } //voglio vedere la mia bacheca
                request.setAttribute("x", u); //mi serve nel jsp per decidere chi è l'autore dei post
                List<Post> p = PostFactory.getInstance().getPostByUser(u);
                if(p != null)
                    session.setAttribute("post", p);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
        }
        else
            request.getRequestDispatcher("negato.jsp").forward(request, response);
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
