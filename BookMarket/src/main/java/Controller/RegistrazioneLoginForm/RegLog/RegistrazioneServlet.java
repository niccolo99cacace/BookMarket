package Controller.RegistrazioneLoginForm.RegLog;

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

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private UtenteDAO utenteDAO = new UtenteDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		if (!(email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
		/** primi caratteri: quanti caratteri misti vuoi oppure trattino oppure punto (le email nn accettano altri simboli)
		 * poi una chiocciola obbligatoria , poi quanti caratteri e trattini vuoi seguito da un punto necessario(tutto ciò quante vuolte vuoi)(c'è +)
		 * poi dai 2 a 4 caratteri o trattino ( 2 a 4 perchè di solito sono .it o .com)
		 */
		{
			throw new MyServletException("Email non valida.");
		}

		String password = request.getParameter("password");
		if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
				&& !password.toLowerCase().equals(password) && password.matches("\\w*[0-9]\\w*"))) {
			throw new MyServletException("Password non valida.");//(regular expression)tutti i caratteri che vuoi ma deve necessariamente essere compreso un numero
		}

		String passwordConferma = request.getParameter("passwordConferma");
		if (!password.equals(passwordConferma)) {
			throw new MyServletException("Password e conferma differenti.");
		}

		String nome = request.getParameter("nome");
		if (!(nome != null && nome.trim().length() > 0 && nome.matches("^[A-Z][\\w._+:]{5,14}$"))) { /** /^[ a-zA-Z\u00C0-\u00ff]+$/ **/
			throw new MyServletException("Nome non valido.");
		}
		/** qui sopra vengono fatti i controlli anche dal lato client su pass email nome e conferma pass **/


		Utente utente = new Utente();//creo il nuovo utente e setto tutti i campi
		utente.setPassword(password);
		utente.setNome(nome);
		utente.setEmail(email);
		utenteDAO.doSave(utente); // viene memorizzato il nuovo utente nel DB
		request.getSession().setAttribute("utente", utente); // e memorizzato nella memoria di sessione

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/RegistrazioneSuccesso.jsp");
		requestDispatcher.forward(request, response);
	}
}
