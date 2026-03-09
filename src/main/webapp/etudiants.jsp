<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, ma.est.models.Etudiant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Etudiants - Listes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
	<%  
		if(request.getAttribute("liste") != null){
		
			List<Etudiant> liste = (ArrayList<Etudiant>)request.getAttribute("liste");
			
		%>
	<div class="container">
		<br>
		<figure class="d-flex justify-content-between align-items-center">
			<blockquote class="blockquote mb-0">
				<h1 class="h1">Etudiant</h1>
				<figcaption class="blockquote-footer mb-0 badge text-bg-success">
					<span>Liste des étudiants</span>
				</figcaption>
			</blockquote>

			<a href="etudiant?action=create" class="btn btn-success">Créer</a>
		</figure>
		<%if (session.getAttribute("message") != null) {%>
		<div class="alert alert-primary d-flex align-items-center"
			role="alert">
			<div>
				<%=session.getAttribute("message")%>
			</div>
		</div>
		<% session.removeAttribute("message"); %>
		<%}%>
		<div class="container-sm my-4 p-2 bg-light rounded shadow-sm">
			<table class="table table-striped table-hover rounded align-middle">
				<thead class="table-dark">
					<td>Id</td>
					<td>Nom</td>
					<td>Prenom</td>
					<td>Telephone</td>
					<td>Adresse</td>
					<td>Action</td>
				</thead>
				<tbody class="table-group-divider">

					<% for(Etudiant e : liste) {%>
					<tr>
						<td><span class="badge text-bg-primary"><%= e.getID()  %>
						</span></td>
						<td><%= e.getName()  %></td>
						<td><%= e.getSurname()  %></td>
						<td><%= e.getTelephone()  %></td>
						<td><%= e.getAdresse()  %></td>
						<td class="text-center">
							<div class="d-flex justify-content-between"
								style="width: 100px; margin: 0 auto;">
								<!-- Voir -->
								<a href="etudiant?action=show&id=<%=e.getID()%>"
									class="fas fa-eye text-info me-2 text-decoration-none"
									title="Voir"></a>

								<!-- Modifier -->
								<a href="etudiant?action=edit&id=<%=e.getID()%>"
									class="fas fa-pen text-warning me-2 text-decoration-none"
									title="Modifier"></a>

								<!-- Supprimer -->
								<a type="button" data-bs-toggle="modal"
									data-bs-target="#supprimer<%= e.getID() %>"
									class="fas fa-trash text-danger text-decoration-none"
									title="Supprimer"></a>

								<div class="modal fade" id="supprimer<%= e.getID() %>"
									tabindex="-1" aria-labelledby="supprimerLabel<%= e.getID() %>"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5"
													id="supprimerLabel<%= e.getID() %>">
													Supprimer l'étudiant <span class="badge text-bg-primary">
														<%= e.getID() %></span>
												</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>
											<div class="modal-body">
												Êtes-vous sûr de vouloir supprimer
												<%= e.getName()+" "+e.getSurname() %>
												?
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Annuler</button>
												<a href="etudiant?action=delete&id=<%= e.getID() %>"
													class="btn btn-danger">Confirmer</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</td>

					</tr>



					<% } %>

				</tbody>
			</table>
		</div>



	</div>
	<%		
			}else{
				response.sendRedirect("index.jsp");
			}
		%>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>