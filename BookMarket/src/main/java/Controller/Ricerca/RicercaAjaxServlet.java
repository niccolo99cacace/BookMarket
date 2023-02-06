package Controller.Ricerca;

import Model.Prodotto;
import Model.ProdottoDAO;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RicercaAjax")
public class RicercaAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray prodJson = new JSONArray();
        String query = request.getParameter("q"); //prelevo il parametro stringa da analizzare
        if (query != null) {
            query += "*"; //applichiamo il modificatore al carattere inserito (modificatore che fa parte di BOOLEAN MODE nella parte in SQL)
            //il modificatore * alla fine dei caratteri , fa in modo che vengano restituiti tutti gli elementi che nel nome contengono almeno
            //una parola che comincia con quei caratteri
            //es: cerco "com" a cui viene aggiunto "*" , fa match con "la Divina Commedia" . Nota: SQL non è case sensitive(se cerco le stesse parole in maiuscolo o minuscolo è a stess cos)
            List<Prodotto> prodotti = prodottoDAO.doRetrieveByNome(query, 0, 10);
            for (Prodotto p : prodotti)
                prodJson.put(p.getTitolo()); //aggiungo i titoli al JSON array da mandare alla barra di suggerimenti
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());
        //getWriter() restituisce un oggetto PrintWriter che può inviare testo di caratteri al client , e append aggiunge testo a PrintWriter
    }
}
