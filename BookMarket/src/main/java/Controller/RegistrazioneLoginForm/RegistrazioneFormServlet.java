package Controller.RegistrazioneLoginForm;

import Controller.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistrazioneForm")
public class RegistrazioneFormServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utente") != null) {
			throw new MyServletException("Utente loggato.");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/RegistrazioneForm.jsp");
		requestDispatcher.forward(request, response);
	}
}

/** viene fatto un ulteriore controllo con (utente!=NULL) per vedere se non è già stato fatto il Login
 * se è gia stato fatto si va alla MyServletException(è una ExceptionClass) che mostrerà una pagina con la scritta "Utente loggato"
 *se NON è stato fatto si manda l'utente alla jsp per la Registrazione/Login **/