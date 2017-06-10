/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.nb.servlet;

import amm.nb.factory.PostFactory;
import amm.nb.factory.UtentiRegistratiFactory;
import amm.nb.entita.UtentiRegistrati;
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
public class Profilo extends HttpServlet {

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
        if(r != null)
        {
            //l'utente è loggato? in = true
            boolean flag = (boolean)r;
            if(!flag)
            {
                //accesso negato se l'utente non è loggato
                request.setAttribute("negato",true);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
            else
            {
                UtentiRegistrati u = (UtentiRegistrati)session.getAttribute("user");
                //accesso consenti, raccolta della lista degli utenti e dei gruppi presenti nel sistema
                request.setAttribute("negato",false);
                if(request.getParameter("cancella") != null)
                    //if(PostFactory.getInstance().deleteAllByUser(u) == true)
                        if(UtentiRegistratiFactory.getInstance().deleteUser(u) == true)
                        {
                            session.invalidate();
                            session = request.getSession();
                            request.getRequestDispatcher("descrizione.html").forward(request, response);
                        }
                String nome = request.getParameter("nome"); //richiesta parametri per eventuale modifica profilo
                String cognome = request.getParameter("cognome");
                String stato = request.getParameter("stato");
                String compleanno = request.getParameter("compleanno");
                String foto = request.getParameter("foto");
                String password = request.getParameter("password");
                String cpassword = request.getParameter("cpassword");
                //modifica dei dati
                if(nome != null)
                {
                    if(!nome.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().updateNome(nome, u.getId());
                        u.setNome(nome);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("nome", true);
                    }
                }
                if(cognome != null)
                {
                    if(!cognome.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().updateCognome(cognome, u.getId());
                        u.setCognome(cognome);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("cognome", true);
                    }
                }
                if(stato != null)
                {
                    if(!stato.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().updateFraseBio(stato, u.getId());
                        u.setFraseBio(stato);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("stato", true);
                    }
                }
                if(compleanno != null)
                {
                    if(!compleanno.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().updateDataNascita(compleanno, u.getId());
                        u.setDataNascita(compleanno);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("compleanno", true);
                    }
                }
                if(foto != null)
                {
                    if(!foto.equals(""))
                    {
                        UtentiRegistratiFactory.getInstance().updateUrlProPic(foto, u.getId());
                        u.setUrlProPic(foto);
                        request.setAttribute("erroredati", false);
                        request.setAttribute("foto", true);
                    }
                }
                if(password != null)
                {
                    if(cpassword != null)
                    {
                        if(cpassword.equals(password))
                        {
                            if (!(password.equals("")))
                            {
                                UtentiRegistratiFactory.getInstance().updatePassword(password, u.getId());
                                u.setPassword(password);
                                request.setAttribute("erroredati", false);
                            }
                        }
                        else //le password non corrispondono
                            request.setAttribute("erroredati", true);
                    }
                }
                else if (cpassword != null) //le psw non corrispondono
                    request.setAttribute("erroredati", true);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }
            if(request.getAttribute("erroredati") != null)
                if(request.getAttribute("erroredati").equals(false))
                    session.setAttribute("utenti",UtentiRegistratiFactory.getInstance().getUserList());
        }
        else
        {
            //accesso negato
            request.setAttribute("negato",true);
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
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
