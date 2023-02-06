package Controller.Profilo;

import Controller.MyServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Utente")
public class UtenteServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("utente") == null) //c'è un ulteriore controllo se l'utente ha fatto il login o meno
			throw new MyServletException("Utente non loggato.");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/Profilo.jsp");
		requestDispatcher.forward(request, response);
	}
}
