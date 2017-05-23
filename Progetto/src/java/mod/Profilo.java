/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

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
                {
                    if(request.getParameter("cancella").equals("1"))
                        if(PostFactory.getInstance().deleteAllByUser(u) == true)
                            if(UtentiRegistratiFactory.getInstance().deleteUser(u) == true)
                            {
                                session.invalidate();
                                session = request.getSession();
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                            }
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
                        u.setNome(nome);
                        request.setAttribute("erroredati", false);
                    }
                }
                if(cognome != null)
                {
                    if(!cognome.equals(""))
                    {
                        u.setCognome(cognome);
                        request.setAttribute("erroredati", false);
                    }
                }
                if(stato != null)
                {
                    if(!stato.equals(""))
                    {
                        u.setFraseBio(stato);
                        request.setAttribute("erroredati", false);
                    }
                }
                if(compleanno != null)
                {
                    if(!compleanno.equals(""))
                    {
                        u.setDataNascita(compleanno);
                        request.setAttribute("erroredati", false);
                    }
                }
                if(foto != null)
                {
                    if(!foto.equals(""))
                    {
                        u.setUrlProPic(foto);
                        request.setAttribute("erroredati", false);
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
