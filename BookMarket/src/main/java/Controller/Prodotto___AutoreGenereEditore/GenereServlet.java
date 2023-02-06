package Controller.Prodotto___AutoreGenereEditore;

import Controller.MyServletException;
import Model.Genere;
import Model.GenereDAO;
import Model.Prodotto;
import Model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Genere")
public class GenereServlet extends HttpServlet {
	private final GenereDAO genereDAO = new GenereDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gen = request.getParameter("id");
		Genere genere = genereDAO.doRetrieveByName(gen); //prende il genere cn relativo id(nome) dal DB
		//c'è una view con tutti i generi

		if (genere == null) {
			throw new MyServletException("Genere non trovato."); //MyServletException
		}
		ProdottoDAO prodottoDAO = new ProdottoDAO();

		int pag = 1;
		String pagstr = request.getParameter("pag");//controlliamo la pagina in cui siamo a meno che non sia la prima
		//per pagina si intende la pagina(una o più) dove vengono visualizzati i libri di un certo genere
		if (pagstr != null) //se è null allora è 1
			pag = Integer.parseInt(pagstr);

		request.setAttribute("pag", pag);//viene settato per essere passato alla jsp

		int perpag = 10; // prodotti per pagina

		int totaleprodotti = prodottoDAO.countByGenere(genere.getNome());//calcolo il numero di prodotti di quel genere
		int npag = (totaleprodotti + perpag - 1) / perpag;//faccio questo calcolo per determinare il numero di pagine che servono per rappresentarli
		request.setAttribute("npag", npag);

		List<Prodotto> prodotti = prodottoDAO.doRetrieveByGenere(genere.getNome(), (pag - 1) * perpag, perpag);
		//prendo i 10 libri da mettere nella pagina; se tipo si tratta della seconda pagina allora prendo quelli dal 10 al 20 ecc.

		request.setAttribute("prodotti",prodotti);
		request.setAttribute("genere", genere);


		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/genere.jsp");
		requestDispatcher.forward(request, response);
	}
}
