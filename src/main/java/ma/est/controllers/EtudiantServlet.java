package ma.est.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.est.models.Etudiant;
import ma.est.models.EtudiantDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet pour gérer les opérations CRUD sur les étudiants.
 * <br>
 * URLs et actions gérées :
 * <ul>
 *   <li>/etudiant : affiche la liste des étudiants</li>
 *   <li>/etudiant?action=show&amp;id=XX : affiche un étudiant</li>
 *   <li>/etudiant?action=create : formulaire de création</li>
 *   <li>/etudiant?action=insert : insert en base</li>
 *   <li>/etudiant?action=edit&amp;id=XX : formulaire de modification</li>
 *   <li>/etudiant?action=update : mise à jour</li>
 *   <li>/etudiant?action=delete&amp;id=XX : suppression</li>
 * </ul>
 * <p>
 * Méthodes HTTP : <br>
 *   - GET  : list, show, create, edit, delete <br>
 *   - POST : insert, update <br>
 *
 * Exemple d'utilisation : <br>
 *   GET /etudiant?create <br>
 *   POST /etudiant?action=delete&amp;id=5 <br>
 * </p>
 * @author Brondon HOUAKEU
 * @version 1.0
 * @see ma.est.models.EtudiantDAO
 */
@WebServlet(urlPatterns = "/etudiant", loadOnStartup = 1)

public class EtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudiantDAO DAO;

	 /**
     * Initialise la servlet et le DAO MySQL.
     * 
     * @throws ServletException si le DAO ne peut pas être initialisé
     */
     @Override
     public void init() throws ServletException {
    	    try {
    	          
    	        DAO = new EtudiantDAO();

    	        System.out.println("INIT SERVLET OK - MySQL");
    	    } catch (ClassNotFoundException e) {
    	        throw new ServletException("Impossible de charger le driver MySQL", e);
    	    }
    	}
    /**
     * Contructeur vide de la servlet.
     * @see HttpServlet#HttpServlet()
     */
    public EtudiantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * Traite les requêtes GET pour afficher un étudiant, <br>
     * 										 la liste des étudiants, <br>
     * 										 le formulaire de création d'un étudiant, <br>
     * 										 le formualire de modification d'un étudiant, <br>
     * et aussi supprimer un étudiant.
     * 
     * @param request  la requête HTTP
     * @param response la réponse HTTP
     * @throws ServletException si une erreur de servlet survient
     * @throws IOException si une erreur d'I/O survient
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action") !=null) {
			switch(request.getParameter("action").toLowerCase()) {
				case "show" : 
				{
					if(request.getParameter("id")!=null) {
						try {
							Etudiant e = DAO.find(Integer.parseInt(request.getParameter("id")));
							request.setAttribute("etudiant", e);
							request.getRequestDispatcher("/show.jsp").forward(request, response);
						} catch (NumberFormatException | SQLException e) {
							
							e.printStackTrace();
						}
					}else {
						response.sendRedirect("/etudiants.jsp");
					}
				}
				break;
				case "edit" : 
				{
					if(request.getParameter("id")!=null) {
						try {
							Etudiant e = DAO.find(Integer.parseInt(request.getParameter("id")));
							request.setAttribute("etudiant", e);
							request.getRequestDispatcher("/edit.jsp").forward(request, response);
						} catch (NumberFormatException | SQLException e) {
							
							e.printStackTrace();
						}
					}
				}
				break;

				case "create" : 
				{
						response.sendRedirect("create.jsp");
				}
				break;

				case "delete" :
				{
					if(request.getParameter("id")!=null) {
						try {
							Etudiant e = DAO.find(Integer.parseInt(request.getParameter("id")));
							if(DAO.delete(e)) {
								request.getSession().setAttribute("message", "Etudiant supprimé avec succès");
								response.sendRedirect("etudiant");
							}else {
								request.getSession().setAttribute("message", "Impossible d'effectuer la suppression");
								response.sendRedirect("etudiant");
							}
						} catch (NumberFormatException | SQLException e) {
							
							e.printStackTrace();
						}
					}
				}
				break;
				default:
				{
					try {
						List<Etudiant> liste = DAO.all();
						request.setAttribute("liste", liste);
						request.getRequestDispatcher("/etudiants.jsp").forward(request, response);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}	
				}
				break;
			
			}
		}else {
			try {
				List<Etudiant> liste = DAO.all();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/etudiants.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
		}
		
		
	}


	/**
	 * Traite les requêtes POST pour insert / update d'un étudiant.
     * 
     * @param request  la requête HTTP
     * @param response la réponse HTTP
     * @throws ServletException si une erreur de servlet survient
     * @throws IOException si une erreur d'I/O survient
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action") !=null) {
			switch(request.getParameter("action").toLowerCase()) {
				
				case "update" : 
				{
					if(request.getParameter("id")!=null) {
						try {
							Etudiant e = DAO.find(Integer.parseInt(request.getParameter("id")));
							if((request.getParameter("adresse").isEmpty()) || (request.getParameter("name").isEmpty()) || (request.getParameter("surname").isEmpty()) || (request.getParameter("telephone").isEmpty())) {
								request.setAttribute("message", "Impossible d'effectuer la mise à jour");
								request.getRequestDispatcher("/edit.jsp").forward(request, response);
							}
							e.setAdresse(request.getParameter("adresse"));
							e.setName(request.getParameter("name"));
							e.setSurname(request.getParameter("surname"));
							e.setTelephone(request.getParameter("telephone"));
							if(DAO.update(e, Integer.parseInt(request.getParameter("id")))){
								request.setAttribute("etudiant", e);
								request.setAttribute("message", "Mise à jour éffectuée avec succès");
								request.getRequestDispatcher("/edit.jsp").forward(request, response);
							}else {
								request.setAttribute("message", "Impossible d'effectuer la mise à jour");
								request.getRequestDispatcher("/edit.jsp").forward(request, response);
							}
							
						} catch (NumberFormatException | SQLException ex) {
							
							ex.printStackTrace();
						}
					}
				}
				break;
				
				case "insert" :
				{
				
					try {
						Etudiant e = new Etudiant();
						if((request.getParameter("adresse").isEmpty()) || (request.getParameter("name").isEmpty()) || (request.getParameter("surname").isEmpty()) || (request.getParameter("telephone").isEmpty())) {
							request.setAttribute("message", "Impossible d'effectuer l'aout");
							request.getRequestDispatcher("/create.jsp").forward(request, response);
						}
						e.setAdresse(request.getParameter("adresse"));
						e.setName(request.getParameter("name"));
						e.setSurname(request.getParameter("surname"));
						e.setTelephone(request.getParameter("telephone"));
						if(DAO.insert(e)){
							request.setAttribute("message", "Etudiant ajouté avec succès");
							request.getRequestDispatcher("/create.jsp").forward(request, response);
						}else {
							request.setAttribute("message", "Impossible d'effectuer l'aout");
							request.getRequestDispatcher("/create.jsp").forward(request, response);
						}
	
					} catch (NumberFormatException | SQLException ex) {
	
						ex.printStackTrace();
					}
	
				}
				break;
				
				default:
				{
					try {
						List<Etudiant> liste = DAO.all();
						request.setAttribute("liste", liste);
						request.getRequestDispatcher("/etudiants.jsp").forward(request, response);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}	
				}
				break;
			
			}
		}
	}

}
