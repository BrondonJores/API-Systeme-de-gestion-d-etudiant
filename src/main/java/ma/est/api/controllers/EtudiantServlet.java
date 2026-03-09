package ma.est.api.controllers;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ma.est.models.Etudiant;
import ma.est.models.EtudiantDAO;
import ma.est.models.EtudiantList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/api/etudiant/*")

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String path = request.getPathInfo();
	    if(path == null || path.equals("/")) {
	    	
			try {
				List<Etudiant> liste = DAO.all();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
		        return;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
	    }

	    if(path.equals("/liste")) {
	        traitementListe(request, response);
	    } else if(path.matches("/\\d+")) {
	    	int id = Integer.parseInt(path.substring(1));
	    	request.setAttribute("id", id);
	        traitementParId(request, response);
	    } else {
	        request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
	        return;
	    }
	}

	
	private void traitementListe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Etudiant> liste = DAO.all();
			String format = request.getParameter("format");
			
			if(format.equals("xml")) {
				response.setContentType("application/xml");
				response.setHeader("Content-Disposition", "attachment; filename=\"etudiants.xml\"");
				EtudiantList list = new EtudiantList(liste);
				marshallXML(list, response.getWriter(), "etudiants.xml");
			}else {
				response.setContentType("application/json");
				response.setHeader("Content-Disposition", "attachment; filename=\"etudiants.json\"");
				writeJSON(liste, response.getWriter(), "etudiants.json");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}


	private void traitementParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();

		if (path != null && path.matches("/\\d+")) {
			try {
				List<Etudiant> liste = new ArrayList<Etudiant>();
				
				Etudiant e = DAO.find(Integer.parseInt(path.substring(1)));
				liste.add(e);
				EtudiantList list = new EtudiantList(liste);
				String filename = e.getName() + "_" + e.getSurname() + ".xml";
				String filenameJSON = e.getName() + "_" + e.getSurname() + ".json";
				String format = request.getParameter("format");
				
				if(format.equals("xml")) {
					response.setContentType("application/xml");
					response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
					marshallXML(list, response.getWriter(), filename);
				}else {
					response.setContentType("application/json");
					response.setHeader("Content-Disposition", "attachment; filename=\""+filenameJSON+"\"");
					writeJSON(liste, response.getWriter(), filenameJSON);
				}
				
			} catch (NumberFormatException | SQLException e) {
				
				e.printStackTrace();
			}
		}else {
			response.sendError(404, "Entrer un ID et réessayer");
		}
	}
	
	private void marshallXML(Object obj, Writer w, String nomfichier) {
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(obj, w);
			marshaller.marshal(obj, new File(nomfichier));
		} catch (JAXBException e) {
			
			e.printStackTrace();
		}
		
		
	}

	private void writeJSON(Object obj, Writer w, String nomfichier) throws IOException {
		JsonbConfig config = new JsonbConfig().withFormatting(true);   

	    Jsonb jsonb = JsonbBuilder.create(config);
		String json = jsonb.toJson(obj);
		w.write(json);
		try(FileWriter fw = new FileWriter(nomfichier)){
			fw.write(json);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
