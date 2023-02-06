package Controller;



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

@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        GenereDAO serviceGen = new GenereDAO();
        List<Genere> generi = serviceGen.doRetrieveAll();
        getServletContext().setAttribute("generi", generi);
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /** vengono caricati (in due liste diverse) i primi 6 prodotti(libri) e poi i primi 6 tra gli ultimi usciti nella request ; poi index.jsp **/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Prodotto> prodotti = serviceProd.doRetrieveAll(0, 6);
        List<Prodotto> ultimi = serviceProd.doRetrieveLast(0,6);
        request.setAttribute("prodotti", prodotti);
        request.setAttribute("ultimi",ultimi);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");
        requestDispatcher.forward(request, response);
    }



    ProdottoDAO serviceProd = new ProdottoDAO();
}