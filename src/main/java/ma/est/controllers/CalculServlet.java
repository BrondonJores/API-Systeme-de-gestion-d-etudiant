package ma.est.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CalculServlet
 */
@WebServlet("/Calcul")
public class CalculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreur", "Erreur tout les champs doivent être rempli");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameter("nbr2").isEmpty() && !request.getParameter("nbr1").isEmpty()) {
			int nbr1 = Integer.parseInt(request.getParameter("nbr1"));
			int nbr2 = Integer.parseInt(request.getParameter("nbr2"));
			
			int s = nbr1+nbr2;
			request.setAttribute("Somme", s);
			request.getRequestDispatcher("calcul.jsp").forward(request, response);
			
		}else {
			request.setAttribute("erreur", "Erreur tout les champs doivent être rempli");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
