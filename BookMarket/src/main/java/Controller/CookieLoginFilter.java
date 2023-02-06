package Controller;/*
package Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.LoginDAO;
import Model.Login;
import Model.LoginDAO;
import Model.Utente;
import Model.UtenteDAO;


@WebFilter("/*") //filtro allplicato a tutti gli URL al deploy
public class CookieLoginFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	private final UtenteDAO utenteDAO = new UtenteDAO();
	private final LoginDAO loginDAO = new LoginDAO();

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = request.getRequestURI();//prendiamo il path corrente
		if (!path.contains("/Login") && !path.contains("/Logout")) {//non ci troviamo ne nella servlet di logout ne in quella di login
			HttpSession session = request.getSession();
			Utente utente = (Utente) session.getAttribute("utente");
			if (utente == null) {
				Cookie cookies[] = request.getCookies();//cookie mandato dalla request dell'utente
				// cookie con nome 'login' o null se non esiste
				Cookie loginCookie = cookies == null ? null
						: Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);/* filtra i cookie cercando quelli con nome 'login' o null se non esiste
//se cookies==null allora loginCookie=null invece se cookies!=null allora loginCookie=al cookie cercato da Arrays

				if (loginCookie != null) {
					String[] sp = loginCookie.getValue().split("_");
					String id = sp[0];
					String token = sp.length > 1 ? sp[1] : null;

					Login login = loginDAO.doRetrieveById(id);
					if (login != null && login.getToken().equals(token)) {
						utente = utenteDAO.doRetrieveById(login.getIdUtente());
						session.setAttribute("utente", utente);

						// per maggiore sicurezza genera nuovo token
						token = UUID.randomUUID().toString();
						login.setToken(token);
						loginDAO.doUpdate(login);
						loginCookie.setValue(id + "_" + token);
						loginCookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
						response.addCookie(loginCookie);
					} else {
						// andrebbe gestita questa eventualità, ad esempio annullando tutte le sessioni
						// dell'utente
						loginCookie.setMaxAge(0);
						response.addCookie(loginCookie);
						if (login != null) {
							loginDAO.doDelete(id);
						}
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

}
*/