package Controller.Profilo.Admin;

import Controller.MyServletException;
import Model.Utente;
import Model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminUtenti")
public class AdminUtentiServlet extends HttpServlet {
	private final UtenteDAO utenteDAO = new UtenteDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /** questa Servlet ,solo per l'admin, gestisce sia il caso in cui da Profilo.jsp l'admin decide di premere il bottone
     * MODIFICA UTENTE , ma anche quando dopo aver premuto MODIFICA UTENTE , l'admin decide se modificare o rimuovere
     * degli utenti
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if (utente == null || !utente.isAdmin()) //controllo che l'utente sia amministratore
            throw new MyServletException();
        String address = "WEB-INF/jsp/adminUtenti.jsp"; //è la jsp dove stanno i bottoni MODIFICA e RIMUOVI

        /** se premiamo bottone MODIFICA UTENTI (siamo in Profilo.jsp)**/
        String idstr = request.getParameter("id");
        if(idstr == null) {//se l'id preso dalla richiesta è vuoto(quindi l'admin ha premuto il bottone MODIFICA UTENTI dal Profilo.jsp)
            List<Utente> utenti = utenteDAO.doRetrieveAll(0, 10); // carico tutti gli utenti dal DB
            request.setAttribute("utenti", utenti); //e li mando alla jsp tramite la request
            //per poi saltare tutto e arrivare direttamente al forward che mi manda alla jsp con tutti gli utenti ed i
            //bottoni MODIFICA e RIMUOVI
        }

        else{
            int id = Integer.parseInt(idstr);

            /** se premiamo bottone RIMUOVI **/
            if(request.getParameter("rimuovi") != null){ //se l'admin ha premuto su RIMUOVI
                utenteDAO.doDelete(id);//rimuovo l'utente
                request.setAttribute("notifica", "Utente RIMOSSO con successo");//notifico la rimossione e torno alla pagina con i 2 bottoni(MODIFICA e RIMUOVI)
                List<Utente> utenti = utenteDAO.doRetrieveAll(0, 10); // ricarico tutti gli utenti aggiornati dal DB e li mando alla jsp
                request.setAttribute("utenti", utenti);
            }

            /** se premiamo bottone MODIFICA (dopo aver compilato i campi da modificare) **/
            else {
                String nome = request.getParameter("nome");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String admin = request.getParameter("admin");//prelevo i nuovi dati inseriti per la modifica
                if ((nome != null) && (email != null)) {
                    Utente u = utenteDAO.doRetrieveById(id);
                    u.setNome(nome);
                    u.setEmail(email);
                    if((password != null) || (password.compareTo("") != 0))
                        u.setPassword(password);//viene settata la password calcolando anche l'hash da memorizzare
                    utenteDAO.doUpdate(u); //faccio i controlli e setto i nuovi dati aggiornando l'utente sul DB
                    request.setAttribute("notifica", "Utente Modificato con successo");
                    List<Utente> utenti = utenteDAO.doRetrieveAll(0, 10); // ricarico tutti gli utenti aggiornati dal DB e li mando alla jsp
                    request.setAttribute("utenti", utenti);
                }
                /** premiamo il bottone MODIFICA ma qui ci troviamo nella parte con i 2 bottoni MODIFICA e RIMUOVI ,
                 * quindi direzioniamo l'admin nella jsp dove può modificare i campi dell'utente */
                else {
                    Utente u = utenteDAO.doRetrieveById(id);
                    request.setAttribute("cliente", u);
                    address = "WEB-INF/jsp/modificaUtenteAdmin.jsp";
                }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
        requestDispatcher.forward(request, response);
    }
}
