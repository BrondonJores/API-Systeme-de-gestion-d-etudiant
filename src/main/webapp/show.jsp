<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, ma.est.models.Etudiant"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Affichage étudiant</title>
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
		if(request.getAttribute("etudiant") != null){
		
			Etudiant e = (Etudiant)request.getAttribute("etudiant");
			
		%>
	<div class="container">
		<br>
		<figure class="d-flex justify-content-between align-items-center">
			<blockquote class="blockquote mb-0">
				<h1 class="h1">Etudiant</h1>
				<figcaption class="blockquote-footer mb-0 badge text-bg-info">
					<span>Bienvenue sur la fiche de <%=e.getName()+" "+e.getSurname() %></span>
				</figcaption>
			</blockquote>

			<a href="etudiant?action=edit&id=<%=e.getID() %>" class="btn btn-info">Modifier</a>
		</figure>

		<div class="container-sm my-4 p-3 bg-light rounded shadow-sm">
			<div class="card mb-3">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="<%=request.getContextPath()%>/resources/images/img1.png"
							class="img-fluid rounded-start">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<div class="d-flex justify-content-between"
								style="margin-bottom: 0px;">
								<h5 class="card-title"><%=e.getName() + " " + e.getSurname()%>

								</h5>

								<h2 class="badge text-bg-info"><%= e.getID()  %>
								</h2>

							</div>
							<hr class="border border-top border-dashed border-info rounded">
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1"> Nom </span> <input
									type="text" class="form-control" value="<%=e.getName()%>"
									aria-label="Username" readonly> <span
									class="input-group-text" id="basic-addon1"> Prenom </span> <input
									type="text" class="form-control" value="<%=e.getSurname()%>"
									aria-label="Username" readonly>
							</div>

							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1">
									Telephone </span> <input type="text" class="form-control"
									value="<%=e.getTelephone()%>" aria-label="Username" readonly>
							</div>

							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1"> Adresse
								</span> <input type="text" class="form-control"
									value="<%=e.getAdresse()%>" aria-label="Username" readonly>
							</div>
						</div>


					</div>

				</div>
			</div>
			<div class="input-group mb-3" style="padding-left: 90%">
				<a href="etudiant" class="input-group-text btn btn-secondary"
					id="basic-addon1"> Retour </a>
			</div>
		</div>

	</div>
	<%		
			}
		%>

</body>
</html>