package Controller.Carrello;

import Controller.MyServletException;
import Model.Carrello;
import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AcquistoCompletato")
public class AcquistoCompletatoServlet extends HttpServlet {
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
	    Utente utente = (Utente) session.getAttribute("utente");

	    if(utente == null)
	    	throw new MyServletException("Effettuare il login");
        for (Carrello.ProdottoQuantita pq : carrello.getProdotti())//faccio passare una variabile che otterrà di volta in
            //volta tutti i valori dei prodotti contenuti nel carrello
        {
            Prodotto p = prodottoDAO.doRetrieveById(pq.getProdotto().getId()); // prendo il prodotto del carrello dal DB
            p.setcopie(p.getcopie() - pq.getQuantita()); //sottraggo alla quantità di prodotto dal DB quella acquistata
            prodottoDAO.doUpdate(p);//aggiorno prodotto nel Db per aggiornare la quantità sotratta
            carrello.remove(p.getId()); //rimuovo dal carrello il prodotto cn il determinato id
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/acquisto.jsp");
        requestDispatcher.forward(request, response);
    }
}
