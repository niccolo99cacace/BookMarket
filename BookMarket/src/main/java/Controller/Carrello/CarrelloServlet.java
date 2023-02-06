package Controller.Carrello;

import Model.Carrello;
import Model.Carrello.ProdottoQuantita;
import Model.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** viene chiamata questa Servlet sia per rimuovere prodotti dal carrello , sia per aggiungerli
 * in entrambi i casi si usa prodId .Si chiama cosi in prodotto.jsp(aggiungere al carrello). E in carrello.jsp(per rimuovere) **/
@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello"); //riprendo il carrello dalla sessione corrente
		//nel caso non esista già
		if (carrello == null) { //se non esiste lo creo
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}

		String prodIdStr = request.getParameter("prodId");//se voglio aggiungere un nuovo prodotto al carrello allora ci sarà l'id del prodotto da prelevare
		/** per AGGIUNGERE prodotto **/
		if (prodIdStr != null) {
			int prodId = Integer.parseInt(prodIdStr);

			String addNumStr = request.getParameter("addNum");//prelevo anche la quantità(addNum si trova in prodotto.jsp)
			if (addNumStr != null) {
				int addNum = Integer.parseInt(addNumStr);
				ProdottoQuantita prodQuant = carrello.get(prodId);//prelevo la quantità del prodottog ià presente nel carrello
				//(nel caso in cui ci sia già una certa quantità di quello specifico prodotto nel carrello)
				if (prodQuant != null) {//se viene confermato il fatto che il carrello contiene già quel prodotto
					prodQuant.setQuantita(prodQuant.getQuantita() + addNum); //ci aggiungo l'altra quantità
				} else {
					carrello.put(prodottoDAO.doRetrieveById(prodId), addNum);//se no aggiungo il nuovo prodotto con quantità selezionata dall'utente
				}
			}

			/** per RIMUOVERE prodotto **/
			else { //se voglio rimuovere un prodotto
				String setNumStr = request.getParameter("setNum");//(setNum si trova in carrello.jsp ed ha valore 0)
				if (setNumStr != null) {//mi assicuro che sia stato premuto il tasto rimuovi
					int setNum = Integer.parseInt(setNumStr);
					carrello.remove(prodId);


					/** COSE PROBABILMENTE INUTILI
					 *
					 * if (setNum <= 0) {
					 * 						carrello.remove(prodId);
					 *                                        } else {
						ProdottoQuantita prodQuant = carrello.get(prodId);
						if (prodQuant != null) {
							prodQuant.setQuantita(setNum);
						} else {
							carrello.put(prodottoDAO.doRetrieveById(prodId), setNum);
						}
					} **/
				}
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
		requestDispatcher.forward(request, response);
	}
}
