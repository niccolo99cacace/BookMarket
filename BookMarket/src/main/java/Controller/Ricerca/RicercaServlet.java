package Controller.Ricerca;

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

@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dest;
		String genere = request.getParameter("category");
		String query = request.getParameter("q");

		int pag = 1;

		String pagstr = request.getParameter("pag");
		if (pagstr != null)
			pag = Integer.parseInt(pagstr);
		request.setAttribute("pag", pag);

		int perpag = 10; // prodotti per pagina
		int totaleprodotti=0;

		//nel caso in cui non sia stato scelto un genere e non si è immesso niente nella barra di ricerca e c'è INVIO
		if( ((genere == null)||(genere.compareTo("") == 0)) && ((query == null)||(query.compareTo("") == 0)) ) {
			totaleprodotti = prodottoDAO.countAll();
			List<Prodotto> prodotti = prodottoDAO.doRetrieveAll((pag - 1) * perpag, perpag);
			request.setAttribute("prodotti", prodotti);
			dest = "WEB-INF/jsp/ricerca.jsp";
		}
		//se invece è stato scelto il genere ma non è stato messo niente nella barra di ricerca
		//si reindirizza la request alla servlet GenereServlet assieme al tipo di genere
		else if((query == null)||((query.compareTo("") == 0))) {
			dest = "/Genere?id="+genere;
		}
		//se il genere è nullo ed è stato però scritto il nome del libro
		else if( (genere == null) || (genere.compareTo("") == 0) ){
			List<Prodotto> prodotti = prodottoDAO.doRetrieveByNomeOrDescrizione(query, (pag - 1) * perpag, perpag);
			totaleprodotti = prodotti.size();
			request.setAttribute("prodotti", prodotti);
			dest = "WEB-INF/jsp/ricerca.jsp";
		}
		//se mettiamo nome e genere allora ti farà la ricerca in base al nome ,però se il libro non coincide con il genere scelto,ti da "nessun prodotto trovato"
		else{
			List<Prodotto> prodotti = prodottoDAO.doRetrieveByNomeOrDescrizioneAndGenere(query, genere, (pag - 1) * perpag, perpag);
			totaleprodotti = prodotti.size();
			request.setAttribute("prodotti", prodotti);
			dest = "WEB-INF/jsp/ricerca.jsp";
		}

		int npag = (totaleprodotti + perpag - 1) / perpag;
		request.setAttribute("npag", npag);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(dest);
		requestDispatcher.forward(request, response);
	}
}
