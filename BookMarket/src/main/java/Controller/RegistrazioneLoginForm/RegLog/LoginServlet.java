package Controller.RegistrazioneLoginForm.RegLog;

import Controller.MyServletException;
import Model.Login;
import Model.LoginDAO;
import Model.Utente;
import Model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private final UtenteDAO utenteDAO = new UtenteDAO();
	private final LoginDAO loginDAO = new LoginDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Utente utente = null;
		if (email != null && password != null) {
			utente = utenteDAO.doRetrieveByEmailPassword(email, password);//ritorna l'utente con queste pass e email
		}

		if (utente == null) {
			throw new MyServletException("email e/o password non validi.");
		}

		Login login = new Login();           //viene creato un nuovo Login e settati i parametri
		login.setIdUtente(utente.getId());
		login.setToken(UUID.randomUUID().toString());//crea un token ,ovvero una sorta di altro id identificativo univoco
		login.setTime(Timestamp.from(Instant.now())); //viene settato il tempo al momento del nuovo Login

		loginDAO.doSave(login);  //viene inserito il nuovo Login nel database

		Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
		cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
		response.addCookie(cookie); //settiamo il cookie e lo mettiamo nella risposta

		request.getSession().setAttribute("utente", utente); //aggiungiamo l'utente alla memoria di sessione


/** grazie all'header referer dalla Servlet RegistrazioneForm facendo il login si viene reindirizzati alla Homepage
 * NOTA: i dest==null , dest.trim().isEmpty() , vengono messi poichè ci sono diversi motivi per cui il referer della
 * richiesta può essere vuoto o nullo .
  */
		String dest = request.getHeader("referer");
		if ( (dest == null || dest.contains("/Login") || dest.contains("/Registrazione") || dest.trim().isEmpty()) ) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}
}
