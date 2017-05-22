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
        //sessione
        HttpSession session = request.getSession();
        //utente loggato?
        Object in = session.getAttribute("in");
        if(in != null)
        {
            boolean flag = (boolean)in;
            if(!flag)
            {
                request.setAttribute("negato",true);
                request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            }
            else
            {
                in = session.getAttribute("user"); //utente loggato: chi è (riutilizzo della variabile in)
                request.setAttribute("negato",false);
                //raccolta parametri get: visualizza bacheca oppure visualizza gruppo?
                Object vb = request.getParameter("visualizza_bacheca"); //utente del quale si vuole visualizzare la bacheca
                Object vg = request.getParameter("visualizza_gruppo");
                UtentiRegistrati u;
                Gruppi g;
                //se visualizza gruppi è presente nella query string
                if(vg != null)
                {
                    //cercare il gruppo corrispondente e mostrare i post
                    int n = Integer.parseInt(vg.toString());
                    g = GruppiFactory.getInstance().getGroupById(n);
                    if(g != null)
                    {
                        request.setAttribute("x", g);
                        //mi serve nel jsp per mostrare i post del gruppo
                        List<Post> p = PostFactory.getInstance().getPostByGroup(g);
                        if(p != null)
                            request.setAttribute("post", p);
                    }
                } //se visualizza bacheca è presente nella query string
                else if(vb != null)
                {
                    //f sta per voglio visualizzare la bacheca di un utente (corrente o presente nel sistema)
                    request.setAttribute("f",true);
                    int n = Integer.parseInt(vb.toString());
                    u = UtentiRegistratiFactory.getInstance().getUserById(n);
                    request.setAttribute("x", u); //mi serve nel jsp per decidere chi è l'autore dei post
                    List<Post> p = PostFactory.getInstance().getPostByDest(u);
                    if(p != null)
                        request.setAttribute("post", p);
                }
                else
                //visualizzare la bacheca dell'utente corrente
                {
                    request.setAttribute("f",true);
                    u = (UtentiRegistrati)in;
                    request.setAttribute("x", u);
                    List<Post> p = PostFactory.getInstance().getPostByDest(u);
                    if(p != null)
                        request.setAttribute("post", p);
                }
                //se nella qs è presente almeno uno di questi elementi significa che si sta cercando di inviare un post
                if(request.getParameter("stato") != null || request.getParameter("tipo") != null || request.getParameter("allegato") != null)
                {
                    String testo = request.getParameter("stato");
                    String allegato = request.getParameter("link");
                    String radio = request.getParameter("tipo");
                    Post.pType tipo = null;
                    //tipologia selezionata?
                    if(radio != null)
                    {
                        if(radio.equals("imm"))
                        {
                            //immagine
                            if(allegato != null)
                            {
                                //ci deve essere l'allegato, altrimenti c'è errore
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
                            //tipologia url, è necessario l'allegato
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
                    //solo testo
                    else if(!testo.equals(""))
                    {
                        if(!allegato.equals(""))
                        {
                            request.setAttribute("erroretipo", true);
                            request.setAttribute("inspost", false);
                        }
                        else
                        {
                            request.setAttribute("inspost", true);
                            request.setAttribute("erroretipo", false);
                            tipo = Post.pType.TEXT;
                        }
                    }
                    else if(!allegato.equals(""))
                    {
                        request.setAttribute("erroretipo", true);
                        request.setAttribute("inspost", false);
                    }
                    //se sono stati inseriti dati
                    if(request.getAttribute("erroretipo") != null)
                        //e se l'inserimento è andato a buon fine
                    {
                        if(!(boolean)request.getAttribute("erroretipo"))
                        {
                            request.setAttribute("inspost", true);
                            //creare nuovo post
                            Post n = new Post();
                            n.setAutore((UtentiRegistrati)session.getAttribute("user"));
                            if(vb != null && Integer.parseInt(vb.toString()) != ((UtentiRegistrati)session.getAttribute("user")).getId()) //post su bacheca altrui
                                n.setDestinatario(UtentiRegistratiFactory.getInstance().getUserById(Integer.parseInt(vb.toString())));
                            else if(vg == null)//post su bacheca utente corrente
                                n.setDestinatario(null);
                            else //if(vg != null) //post su gruppo
                                n.setGruppo(GruppiFactory.getInstance().getGroupByName(vg.toString()));
                            n.setTipologia(tipo);
                            if(!testo.equals(""))
                                n.setContenuto(request.getParameter("stato"));
                            if(!allegato.equals(""))
                                n.setAllegato(request.getParameter("link"));
                            PostFactory.getInstance().addNewPost(n);
                            request.setAttribute("n",n);
                        }
                    }
                }
                //conferma invio post
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
            //accesso negato
            request.setAttribute("negato",true);
            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
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
