/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.servlet;

import amm.nb.entita.Gruppi;
import amm.nb.factory.PostFactory;
import amm.nb.factory.UtentiRegistratiFactory;
import amm.nb.entita.UtentiRegistrati;
import amm.nb.factory.GruppiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class Gruppo extends HttpServlet {

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
        //sessione
        HttpSession session = request.getSession();
        //capire se l'utente è loggato
        Object r = session.getAttribute("in");
        Gruppi g = new Gruppi();
        if(r != null)
        {
            //l'utente è loggato? in = true
            boolean flag = (boolean)r;
            if(!flag)
            {
                //accesso negato se l'utente non è loggato
                request.setAttribute("negato",true);
                request.getRequestDispatcher("gruppo.jsp").forward(request, response);
            }
            else
            {
                UtentiRegistrati u = (UtentiRegistrati)session.getAttribute("user");
                //accesso consenti, raccolta della lista degli utenti e dei gruppi presenti nel sistema
                request.setAttribute("negato",false);
                String nome = request.getParameter("nome"); //richiesta parametri per eventuale modifica profilo
                String stato = request.getParameter("descrizione");
                String foto = request.getParameter("foto");
                //modifica dei dati
                if(nome != null)
                {
                    if(!nome.equals(""))
                    {
                        g.setNome(nome);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("nome", true);
                    }
                }
                if(stato != null)
                {
                    if(!stato.equals(""))
                    {
                        g.setDescrizione(stato);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("descrizione", true);
                    }
                }
                if(foto != null)
                {
                    if(!foto.equals(""))
                    {
                        g.setUrlProPic(foto);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("foto", true);
                    }
                }
            if(g.getNome() == null)
            {
                if(g.getDescrizione() != null || g.getUrlProPic() != null)
                    request.setAttribute("erroredati",true);
            }
            if(request.getAttribute("erroredati") != null)
                if(request.getAttribute("erroredati").equals(false))
                {
                    g.setFounder(u);
                    if(GruppiFactory.getInstance().creaGruppo(g))
                        request.setAttribute("erroredati",false);
                    else
                        request.setAttribute("erroredati",true);
                    session.setAttribute("gruppi",GruppiFactory.getInstance().getGroupList());
                    request.setAttribute("g",g);
                }
            }
        }
        else
        {
            //accesso negato
            request.setAttribute("negato",true);
            request.getRequestDispatcher("gruppo.jsp").forward(request, response);
        }
        request.getRequestDispatcher("gruppo.jsp").forward(request, response);
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
