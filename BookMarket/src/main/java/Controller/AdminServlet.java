package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminProdotto")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
        if (utente == null || !utente.isAdmin()) //controllo che l'utente sia amministratore
            throw new MyServletException();

        String idstr = request.getParameter("id"); //prendo l'id del libro da modificare o rimuovere
        if (idstr != null) {
            int id = Integer.parseInt(idstr);

            if (request.getParameter("rimuovi") != null) {  //rimozione (se ha premuto su rimuovi)
                prodottoDAO.doDelete(id);
                request.setAttribute("notifica", "Prodotto RIMOSSO con successo");
            }

            else {  //se no modifico il prodotto
                Prodotto prodotto = prodottoDAO.doRetrieveById(id);

                String titolo = request.getParameter("titolo");
                String descrizione = request.getParameter("descrizione");
                String copie = request.getParameter("copie");
                String prezzoCent = request.getParameter("prezzoCent");
                if((titolo != null) && (descrizione != null) && (copie != null) && (prezzoCent != null)){ //modifica
                    prodotto.setTitolo(titolo);
                    prodotto.setDescrizione(descrizione);
                    int t = Integer.parseInt(copie);
                    if(t >= 0)
                        prodotto.setcopie(Integer.parseInt(copie));
                    prodotto.setPrezzoCent(Long.parseLong(prezzoCent));
                    prodottoDAO.doUpdate(prodotto);
                    request.setAttribute("notifica", "Prodotto modificato con successo");
                }
                request.setAttribute("prodotto", prodotto);
            }
        }
        /** PARTE NN IMPLEMENTATA DEL TUTTO PER AGGIUNGERE PORDOTTO

         else {    //nuovo prodotto
            Prodotto prodotto = null;

            String titolo = request.getParameter("titolo");
            String descrizione = request.getParameter("descrizione");
            String copie = request.getParameter("copie");
            String prezzoCent = request.getParameter("prezzoCent");
            String autore = request.getParameter("autore");
            String editore = request.getParameter("editore");
            String genere = request.getParameter("genere");
            if ((titolo != null) && (descrizione != null) && (copie != null) && (prezzoCent != null) && (autore != null) && (editore != null) && (genere != null)) {
                AutoreDAO autoredao = new AutoreDAO();
                prodotto = new Prodotto();
                prodotto.setTitolo(titolo);
                Autore a = autoredao.doRetrieveById(Integer.parseInt(autore));
                prodotto.setAutore(a);
                prodotto.setEditore(editore);
                prodotto.setDescrizione(descrizione);
                prodotto.setcopie(Integer.parseInt(copie));
                prodotto.setPrezzoCent(Long.parseLong(prezzoCent));

                prodottoDAO.doSave(prodotto);
                request.setAttribute("notifica", "Prodotto aggiunto con successo");
            }
            else
                request.setAttribute("notifica", "Compilare tutti i campi"); //errore
        } **/
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminProdotto.jsp");
        requestDispatcher.forward(request, response);
    }
}
