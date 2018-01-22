/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.nerdbook.servlet;

import project.nerd.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ste2016
 */
@WebServlet(urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {

    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";

    @Override
    public void init() {
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        GruppoFactory.getInstance().setConnectionString(dbConnection);
        PostFactory.getInstance().setConnectionString(dbConnection);
        UtenteFactory.getInstance().setConnectionString(dbConnection);
        /*-----*/
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //apro la sessione 
        HttpSession session = request.getSession();

        //Effettuo il Logout
        if (request.getParameter("logout") != null) {
            session.invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        //eseguo nel caso in cui l'utente sia già loggato
        if (session.getAttribute("loggedIn") != null
                && session.getAttribute("loggedIn").equals(true)) {
            int loggedUserID = (int) session.getAttribute("loggedUserID");
            Utente utenteLoggato = UtenteFactory.getInstance().getUtentebyId(loggedUserID);
            /* se nome cognome o altro sono null, entra nel profilo e non in bacheca ( modifica sql per test*/
            if (utenteLoggato.getNome().equals("") || utenteLoggato.getCognome().equals("")
                    || utenteLoggato.getUrlFoto().equals("") || utenteLoggato.getFrase().equals("")) {
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
            }

            request.getRequestDispatcher("bacheca.jsp").forward(request, response);
            return;

            //L'utente compila il form di login
        } else {
            String email = request.getParameter("username");
            String password = request.getParameter("nome");

            if (email != null && password != null) {

                int loggedUserID = UtenteFactory.getInstance().getIdByEmailAndPassword(email, password);

                //se l'utente è valido
                if (loggedUserID != -1) {

                    session.setAttribute("listaUtenti", UtenteFactory.getInstance().getList());//Utenti iscritti alla piattaforma
                    session.setAttribute("listaGruppi", GruppoFactory.getInstance().getList());//Utenti (gruppi) appartenenti alla piattaforma
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("loggedUserID", loggedUserID);
                    session.setAttribute("utenteLoggato", UtenteFactory.getInstance().getUtentebyId(loggedUserID));

                    Utente utenteLoggato = UtenteFactory.getInstance().getUtentebyId(loggedUserID);
                    /* se nome cognome o altro sono null, entra nel profilo e non in bacheca, in modo da 
                       poter aggiornare i dati
                     */
                    if (utenteLoggato.getNome().equals("") || utenteLoggato.getCognome().equals("")
                            || utenteLoggato.getUrlFoto().equals("") || utenteLoggato.getFrase().equals("")) {
                        request.getRequestDispatcher("profilo.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("bacheca.html").forward(request, response); //mi fa vedere la bacheca con tutti i post, secondo quanto implementato nella servlet 
                    }
                    return; //se tutto va bene esegue la return

                } else {

                    /*Nell'eventualità in cui i dat non siano validi, restituisco un opportuno messaggio di errore
                     attraverso l'utilizzo della variabile di sessione loginError 
                     */
                    request.setAttribute("loginError", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

            }
        }

        /*
          Se non si verifica nessuno degli altri casi, 
          tentativo di accesso diretto alla servlet Login -> reindirizzo verso 
          il form di login.
         */
        request.getRequestDispatcher("login.jsp").forward(request, response);

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
