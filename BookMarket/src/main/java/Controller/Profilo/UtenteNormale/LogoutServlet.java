package Controller.Profilo.UtenteNormale;

import Model.LoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private final LoginDAO loginDAO = new LoginDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("utente"); //rimuove l'utente dalla sessione

		Cookie cookies[] = request.getCookies(); //prende i cookie dalla richesta
		if (cookies != null) {

			/* filtra i cookie cercando quelli con nome 'login' o null se non esiste */
			Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);
			if (cookie != null) {
				cookie.setMaxAge(0); // se il cookie 'login' esiste allora lo rimuove
				response.addCookie(cookie);
				String id = cookie.getValue().split("_")[0];/* il metodo split restituisce un array con gli elementi della
				stringa che precede il punto divisi dal segno usato (_) trattino basso in questo caso */
				loginDAO.doDelete(id); //il login viene eliminato dal DB
			}
		}

		//STESSO RAGIONAMENTO DEL lOGIN
		String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/Logout") || dest.contains("/Utente") || dest.contains("/Modifica") || dest.trim().isEmpty()) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}
}
