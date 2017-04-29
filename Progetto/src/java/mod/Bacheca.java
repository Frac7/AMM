/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod;

import java.util.List;
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
            {
                request.setAttribute("negato",true);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("negato",false);
                List<UtentiRegistrati> l = UtentiRegistratiFactory.getInstance().getUserList();
                session.setAttribute("utenti", l);
                r = session.getAttribute("user"); //utente loggato
                Object s = request.getParameter("visualizza_bacheca"); //utente del quale si vuole visualizzare la bacheca
                UtentiRegistrati u;
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
                    request.setAttribute("post", p);

                if(request.getParameter("stato") != null || request.getParameter("tipo") != null || request.getParameter("allegato") != null)
                {
                    String testo = request.getParameter("stato");
                    String allegato = request.getParameter("link");
                    String radio = request.getParameter("tipo");
                    Post.pType tipo = null;
                    if(radio != null)
                    {
                        if(radio.equals("imm"))
                        {
                            /*//se selezione immagine o url non devo scrivere testo
                            if(testo != null)
                            {
                                if(!(testo.equals("")))
                                    request.setAttribute("erroretipo", true);
                            }*/
                            //se seleziono immagine o url devo scrivere allegato
                            if(allegato != null)
                            {
                                if (!(allegato.equals("")))
                                {
                                    request.setAttribute("multimedia",1);
                                    tipo = Post.pType.IMAGE;
                                    request.setAttribute("erroretipo", false);
                                    request.setAttribute("inspost", true);
                                }
                                else
                                    request.setAttribute("erroretipo", true);
                                    
                            }
                            else
                            {
                                request.setAttribute("erroretipo", true);
                            }
                        }
                        else if(radio.equals("url"))
                        {
                            /*//se selezione immagine o url non devo scrivere testo
                            if(testo != null)
                            {
                                if(!testo.equals(""))
                                    request.setAttribute("erroretipo", true);
                            }*/
                            //se seleziono immagine o url devo scrivere allegato
                            if(allegato != null)
                            {
                                if (!(allegato.equals("")))
                                {
                                    request.setAttribute("multimedia", 2);
                                    tipo = Post.pType.URL;
                                    request.setAttribute("erroretipo", false);
                                    request.setAttribute("inspost", true);
                                }
                                else
                                    request.setAttribute("erroretipo", true);
                                    
                            }
                            else
                            {
                                request.setAttribute("erroretipo", true);
                            }
                        }
                    }
                    //se seleziono testo non devo selezionare altro
                    else if(testo != null)
                    {
                        
                        /*if(allegato != null)
                        {
                            if(!(allegato.equals("")))
                                request.setAttribute("erroretipo", true);
                            else
                            {
                                request.setAttribute("inspost", true);
                                request.setAttribute("erroretipo", false);
                                tipo = Post.pType.TEXT;
                            }
                        }
                        else*/
                        if(!testo.equals(""))
                        {
                            request.setAttribute("inspost", true);
                            request.setAttribute("erroretipo", false);
                            tipo = Post.pType.TEXT;
                        }
                    }
                    //se ho cercato di inserire dati
                    if(request.getAttribute("erroretipo") != null)
                        //e se ci sono riuscita
                    {
                        if(!(boolean)request.getAttribute("erroretipo"))
                        {
                            request.setAttribute("inspost", true);
                            //crea nuovo post
                            Post n = new Post();
                            n.setAutore((UtentiRegistrati)session.getAttribute("user"));
                            if(s != null)
                                n.setDestinatario(UtentiRegistratiFactory.getInstance().getUserByName(s.toString()));
                            else
                                n.setDestinatario((UtentiRegistrati)session.getAttribute("user"));
                            n.setTipologia(tipo);
                            if(tipo == Post.pType.TEXT)
                                n.setContenuto(request.getParameter("stato"));
                            else if(tipo == Post.pType.URL || tipo == Post.pType.IMAGE)
                                n.setContenuto(request.getParameter("allegato"));
                            request.setAttribute("n",n);
                        }
                    }
                }
                if(request.getParameter("conferma") != null)
                {
                    if((request.getParameter("conferma").equals("true")))
                        request.setAttribute("conferma", true);
                }
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
        }
        else
        {
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
