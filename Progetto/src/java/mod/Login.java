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
        //creare/cercare la sessione
        HttpSession session = request.getSession();
        //se si è premuto su logout - parametro get quindi visibile su url
        if(request.getParameter("logout") != null)
        {
            //invalidare la sessione
            if(request.getParameter("logout").equals("1"))
                session.invalidate();
        }
        //parametri della richiesta (dopo aver premuto login)
        String utente = request.getParameter("user");
        String password = request.getParameter("password");
        //se i parametri non sono sulla url (campi vuoti)
        if(utente == null || password == null)
            //mostrare di nuovo la pagina di login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        //se è stato inserito qualcosa (utente)
        if(utente != null)
        {
            //ricavare l'utente tramite corrispondenza per nome - poi per id
            UtentiRegistrati u = UtentiRegistratiFactory.getInstance().getUserByName(utente);
            //se l'utente esiste
            if(u != null)
            {
                //controllare che psw e username corrispondano
                if(u.getNome().equals(utente) && u.getPassword().equals(password))
                {
                    //utente loggato
                    session.setAttribute("in",true);
                    session.setAttribute("user",u); //chi è utente loggato
                    session.setAttribute("x",u); //utente del quale visualizzo la bacheca (dopo login si visualizza la bacheca dell'utente loggato)
                    //se mancano dati
                    if(u.getNome() == null || u.getUrlProPic() == null || u.getCognome() == null || u.getFraseBio() == null)
                        //si visualizza il profilo
                        response.sendRedirect("profilo.html");
                    else
                    {
                        //altrimenti la bacheca con relativa lista post
                        List<Post> p = PostFactory.getInstance().getPostByUser(u);
                        session.setAttribute("post", p);
                        response.sendRedirect("bacheca.html");
                    }
                }
                else
                {
                    //errore
                    request.setAttribute("errore", true);
                    session.setAttribute("in",false);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
            else
            {
                //errore anche se non si inserisce nulla
                request.setAttribute("errore", true);
                session.setAttribute("in",false);
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
