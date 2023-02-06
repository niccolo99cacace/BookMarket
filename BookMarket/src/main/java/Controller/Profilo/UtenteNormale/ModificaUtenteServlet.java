package Controller.Profilo.UtenteNormale;

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


@WebServlet("/ModificaUtente")
public class ModificaUtenteServlet extends HttpServlet {
	UtenteDAO utenteDAO = new UtenteDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Address = "WEB-INF/jsp/ModificaDati.jsp";
		String idstr = request.getParameter("id");

		/* prendo l'id dell'utente ,se non è NULL e non ci sono errori cambio l'indirizzo di destinazione
		  che in caso esista torna al Profilo modificato
		 */

			if (idstr != null) {
				Address = "WEB-INF/jsp/Profilo.jsp";
				Utente u = (Utente) request.getSession().getAttribute("utente");
				int id = Integer.parseInt(idstr);
				if ((u == null) || (u.getId() != id)) //un controllo sull id e sull utente in memoria di sessione
					throw new MyServletException("Errore utente");
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				if ((nome != null) && (email != null)) {    //modifica dati
					u.setNome(nome);
					u.setEmail(email);
					utenteDAO.doUpdate(u);
					request.getSession().setAttribute("utente", u); //aggiorno l'utente nella memoria di sessione
					request.setAttribute("notifica", "Informazioni modificate con successo");
				} else {
					request.setAttribute("notifica", "informazioni insufficienti"); //errore
				}
			}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Address);
		requestDispatcher.forward(request, response);
	}
}