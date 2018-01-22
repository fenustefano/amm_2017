/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.nerdbook.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.nerd.*;

/**
 *
 * @author Ste2016
 */
@WebServlet(urlPatterns =
{
    "/profilo.html"
})
public class Profilo extends HttpServlet
{

    
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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        
        if (session.getAttribute("loggedIn") == null)
        {
            //Utente non loggato
            request.getRequestDispatcher("profilo.jsp").forward(request, response);//cambiare in login
            return;
        }

        if (request.getParameter("aggiorna") != null)
        {
            //String connectionString;
            String nomeUtente = request.getParameter("nomeUtente");
            if (nomeUtente != null && !nomeUtente.equals(""))
            {
              session.setAttribute("nome", nomeUtente); //setting session variable
              session.setAttribute("cambio", "7"); // setting session variable
              
            }
    
            String cognomeUtente = request.getParameter("cognome");
            if (cognomeUtente != null && !cognomeUtente.equals(""))
            {
                request.setAttribute("cognome", cognomeUtente);
                request.setAttribute("cambio", "7");
            }

            String urlFotoProfilo = request.getParameter("UrlImage");
            if (urlFotoProfilo != null && !urlFotoProfilo.equals(""))
            {
                request.setAttribute("url", urlFotoProfilo);
                request.setAttribute("cambio", "7");
            }

            String frasePresentazione = request.getParameter("descrizione");
            if (frasePresentazione != null && !frasePresentazione.equals(""))
            {
                request.setAttribute("frase", frasePresentazione);
                request.setAttribute("cambio", "7");
            }
            
            String pswd = request.getParameter("pswd");
            if (pswd != null && !pswd.equals(""))
            {
                if (pswd.equals(request.getParameter("confPswd")))
                {
                    request.setAttribute("password", pswd);
                    request.setAttribute("cambio", "7");
                } else
                {
                    request.setAttribute("errore_pswd", "errore");
                }
            }
        }
        
        if(request.getParameter("cancella")!=null)
        {
            UtenteFactory.getInstance().cancellaUtente((Utente)session.getAttribute("utenteLoggato"));
            session.invalidate();
            request.getRequestDispatcher("login.html").forward(request, response);
            
        }
        
        request.setAttribute("aggiorna", "7");
        request.getRequestDispatcher("profilo.jsp").forward(request, response);//prima c'era profilo

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
            throws ServletException, IOException
    {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
