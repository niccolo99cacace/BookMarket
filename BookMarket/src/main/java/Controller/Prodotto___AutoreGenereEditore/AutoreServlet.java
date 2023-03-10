package Controller.Prodotto___AutoreGenereEditore;

import Controller.MyServletException;
import Model.Autore;
import Model.AutoreDAO;
import Model.Prodotto;
import Model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/Autore")
public class AutoreServlet extends javax.servlet.http.HttpServlet {
	private final AutoreDAO autoreDAO = new AutoreDAO();

	protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		doGet(request, response);
	}

	//STESSO RAGIONAMENTO DEL GENERE
	protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Autore autore = autoreDAO.doRetrieveById(id);
		ProdottoDAO service = new ProdottoDAO();
		List<Prodotto> prodotti;
		if (autore == null) {
			throw new MyServletException("Prodotto non trovato."); //MyServletException
		}

		ProdottoDAO prodottoDAO = new ProdottoDAO();
		int pag = 1;
		String pagstr = request.getParameter("pag");
		if (pagstr != null)
			pag = Integer.parseInt(pagstr);
		request.setAttribute("pag", pag);

		int perpag = 2; // prodotti per pagina

		int totaleprodotti = prodottoDAO.countByAutore(autore.getId());
		int npag = (totaleprodotti + perpag - 1) / perpag;
		request.setAttribute("npag", npag);

		prodotti = service.doRetrieveByAutore(autore.getId(), (pag - 1) * perpag, perpag);
		request.setAttribute("autore", autore);
		request.setAttribute("prodotti", prodotti);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/autore.jsp");
		requestDispatcher.forward(request, response);
	}
}
