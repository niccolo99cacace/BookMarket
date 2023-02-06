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

@WebServlet("/ModificaPassword")
public class ModificaPasswordServlet extends HttpServlet {
	UtenteDAO utenteDAO = new UtenteDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Address = "WEB-INF/jsp/ModificaPassword.jsp";
		String idstr = request.getParameter("id");
		/* prendo l'id dell'utente ,se non è NULL e non ci sono errori cambio l'indirizzo di destinazione
		  che in caso esista torna al Profilo modificato
		 */

		String notifica = null;
		if (idstr != null) {
			Address = "WEB-INF/jsp/Profilo.jsp";
			Utente u = (Utente) request.getSession().getAttribute("utente");
			int id = Integer.parseInt(idstr);
			if ((u == null) || (u.getId() != id))  //un controllo sull id e sull utente in memoria di sessione
				throw new MyServletException("Errore utente");
			String password = request.getParameter("password");
			String newpassword = request.getParameter("nuovapassword");
			if ((password != null) && (newpassword != null) && (u.checkPassword(password))) {
				u.setPassword(newpassword);
				utenteDAO.doUpdate(u);
				/* modifica password la corrente e la nuova non solo null e se la password vecchia
				inserita coincide cn quella memorizzata nel DB (si confrontano gli hash ovviamente) */

				request.setAttribute("notifica"," password modificata con successo");
				request.getSession().setAttribute("utente", u);
			} else {
				request.setAttribute("notifica", "Errore, password incorretta");
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Address);
		requestDispatcher.forward(request, response);
	}
}
